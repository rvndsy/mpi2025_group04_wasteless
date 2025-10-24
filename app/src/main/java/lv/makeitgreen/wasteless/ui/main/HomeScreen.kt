package lv.makeitgreen.wasteless.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@Composable
fun HomeScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HelloWorld(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun HelloWorld(modifier: Modifier = Modifier) {
    Text(
        text = "Hello World!",
        modifier = modifier
    )
}
