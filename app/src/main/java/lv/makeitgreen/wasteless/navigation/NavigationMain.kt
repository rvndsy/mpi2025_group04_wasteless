package lv.makeitgreen.wasteless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lv.makeitgreen.wasteless.ui.main.HomeScreen
import lv.makeitgreen.wasteless.ui.main.MapScreen
import lv.makeitgreen.wasteless.ui.main.ScanScreen
import lv.makeitgreen.wasteless.ui.main.SearchScreen

@Composable
fun NavigationMain(navController: NavController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(route = Screen.MapScreen.route) {
            MapScreen(navController)
        }
        composable(route = Screen.ScanScreen.route) {
            ScanScreen(navController)
        }
    }
}
