@file:kotlin.OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)

package lv.makeitgreen.wasteless.ui.main

import android.Manifest
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
@Composable
fun ScanScreen(navController: NavController) {
    var context = LocalContext.current
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SCAN) }
    val lifecycleOwner = LocalLifecycleOwner.current
    var detectedBarcode by remember { mutableStateOf<String?>(null) }
    var isCameraPreviewActive by remember { mutableStateOf(false) }
    val cameraPermissions = rememberPermissionState(Manifest.permission.CAMERA)


    // Useful resources
    // https://developer.android.com/media/camera/camerax/preview
    // https://developers.google.com/ml-kit/vision/barcode-scanning/android#kotlin

    if (!isCameraPreviewActive) {
        // Display this while not scanning
        NavBar(currentDestination, navController) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                BarcodeProductSearchbar(
                    searchBarModifier = Modifier.fillMaxWidth(0.95F)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // If a barcode is found, display some info
                    DisplayBarcode(detectedBarcode)
                    Button(
                        modifier = Modifier
                            .padding(top = 96.dp)
                            .fillMaxWidth(0.6F)
                            .height(64.dp),
                        onClick = { isCameraPreviewActive = true }
                    ) {
                        Text(
                            text = "Start scan",
                            fontSize = 20.sp)
                    }
                }
            }
        }
    } else {
        // Ask for camera permissions
        LaunchedEffect(Unit) {
            cameraPermissions.launchPermissionRequest()
        }

        // Set up Camera Preview
        val cameraProvider = ProcessCameraProvider.getInstance(context).get()
        val previewView = remember { PreviewView(context) }
        val preview = Preview.Builder().build().apply {
            setSurfaceProvider(previewView.surfaceProvider)
        }

        // Let the ML Kit Barcode Scanner scan all barcodes
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
        // Get instance of the scanner
        val scanner = BarcodeScanning.getClient(options)

        // Analysis takes an image from Camera Preview with the image rotation info and sends it to Barcode scanner
        // If the scanner finds a barcode, then log it and store it into detectedBarcode
        // and because detectedBarcode is mutableStateOf, ScanScreen gets reloaded with the detectedBarcode as not null
        val analyzer = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also { analysis ->
                analysis.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                    val mediaImage = imageProxy.image
                    if (mediaImage != null) {
                        val image = InputImage.fromMediaImage(
                            mediaImage,
                            imageProxy.imageInfo.rotationDegrees
                        )
                        scanner.process(image)
                            .addOnSuccessListener { barcodes ->
                                for (barcode in barcodes) {
                                    Log.d("ScanScreen", "Barcode detected: ${barcode.rawValue}")
                                    if (barcode.rawValue != null) {
                                        detectedBarcode = barcode.rawValue
                                        isCameraPreviewActive = false
                                    }
                                    imageProxy.close()
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.e("ScanScreen", "Barcode scanning has failed", e)
                            }
                            // always called after Success or Failure
                            .addOnCompleteListener {
                                imageProxy.close()
                            }
                    } else {
                        imageProxy.close()
                    }
                }
            }

        // Lifecycle stuff
        DisposableEffect(isCameraPreviewActive) {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                analyzer
            )

            // Cleanup when exiting Camera preview
            onDispose {
                analyzer.clearAnalyzer()
                cameraProvider.unbindAll()
                isCameraPreviewActive = false
            }
        }


        // Open the camera and start scanning
        AndroidView(
            factory = {
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun DisplayBarcode(barcode: String?) {
    if (barcode == null) {
        Text("No barcode found")
    } else {
        Text(barcode)
    }
}

@Composable
fun BarcodeProductSearchbar(
    searchBarModifier: Modifier
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it
            },
            onSearch = {
                isSearchActive = false
            },
            active = isSearchActive,
            onActiveChange = {
                isSearchActive = it
            },
            placeholder = {
                Text(
                    text = "Search here",
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (isSearchActive) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close Icon",
                        modifier = Modifier.clickable{
                            if (searchText.isEmpty()) {
                                isSearchActive = false
                            } else {
                                searchText = ""
                            }
                        },
                    )
                }
            },
            modifier = searchBarModifier
        ) {}
    }
}