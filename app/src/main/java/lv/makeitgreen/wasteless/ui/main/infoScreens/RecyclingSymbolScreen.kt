package lv.makeitgreen.wasteless.ui.main.infoScreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
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
fun RecyclingSymbolScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            RecyclingSymbolDescriptions(
                modifier = Modifier.fillMaxWidth(0.95F)
            )
        }
    }
}


@Composable
fun RecyclingSymbolDescriptions(
    modifier: Modifier
) {

}