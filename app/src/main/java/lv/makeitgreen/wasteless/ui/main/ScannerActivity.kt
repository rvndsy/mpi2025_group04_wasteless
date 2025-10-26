@file:OptIn(ExperimentalPermissionsApi::class)

package lv.makeitgreen.wasteless.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import lv.makeitgreen.wasteless.ui.theme.WastelessTheme
import java.util.concurrent.Executors

class ScannerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WastelessTheme {
                BarcodeScanner()
            }
        }
    }
}

@Composable
fun BarcodeScanner() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraPermissions = rememberPermissionState(Manifest.permission.CAMERA)
    var detectedBarcode by remember { mutableStateOf<String?>(null) }
    var isCameraPreviewActive by remember { mutableStateOf(false) }

    // Ask for camera permissions
    LaunchedEffect(Unit) {
        cameraPermissions.launchPermissionRequest()
    }

    // Set up Camera Preview
    val cameraProvider = ProcessCameraProvider.getInstance(context).get()
    val previewView = remember { PreviewView(context) }
    val preview = Preview.Builder().build().apply {
        surfaceProvider = previewView.surfaceProvider
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
                                val activity = (context as? Activity)
                                val resultIntent = Intent().apply { putExtra("detectedBarcode", detectedBarcode) }
                                activity?.setResult(Activity.RESULT_OK, resultIntent)
                                activity?.finish()
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
