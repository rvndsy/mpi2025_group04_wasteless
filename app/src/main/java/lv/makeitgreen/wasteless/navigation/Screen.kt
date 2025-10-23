package lv.makeitgreen.wasteless.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object MapScreen : Screen("map_screen")
    object SearchScreen : Screen("search_screen")
    object ScanScreen : Screen("scan_screen")
}
