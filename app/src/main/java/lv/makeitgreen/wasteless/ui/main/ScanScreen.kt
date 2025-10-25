package lv.makeitgreen.wasteless.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@Composable
fun ScanScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SCAN) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HelloScan(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun HelloScan(modifier: Modifier = Modifier) {
    Text(
        text = "Hello, this is the barcode scanner!",
        modifier = modifier
    )
}