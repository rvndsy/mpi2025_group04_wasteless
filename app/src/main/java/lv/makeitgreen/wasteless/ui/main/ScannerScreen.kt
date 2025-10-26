@file:kotlin.OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)

package lv.makeitgreen.wasteless.ui.main

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
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
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@OptIn(ExperimentalGetImage::class)
@Composable
fun ScannerScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SCANNER) }
    var detectedBarcode by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Keep launcher initialization outside of if else, else app crashes
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            detectedBarcode = result.data?.getStringExtra("detectedBarcode")
            //navController.navigate(AppDestinations.SCAN.route)
        } else {
            navController.popBackStack()
        }
    }

    // Useful resources
    // https://developer.android.com/media/camera/camerax/preview
    // https://developers.google.com/ml-kit/vision/barcode-scanning/android#kotlin

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
                    onClick = {
                        // Initialize the ScannerActivity
                        val intent = Intent(context, ScannerActivity::class.java)
                        launcher.launch(intent)
                    }
                ) {
                    Text(
                        text = "Start scan",
                        fontSize = 20.sp)
                }
            }
        }
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

