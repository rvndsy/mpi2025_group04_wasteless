package lv.makeitgreen.wasteless.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import lv.makeitgreen.wasteless.ui.icons.MyIcons

// Part of Navigation Bar
enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {
    // Order top to bottom here is the same as left to right in the NavBar
    HOME("Home", Icons.Rounded.Home, "home_screen"),
    MAP("Map", Icons.Rounded.Place, "map_screen"),
    SCANNER("Scanner", MyIcons.OutlineBarcodeScanner24, "scanner_screen"),
    SEARCH("Search", Icons.Rounded.Search, "search_screen"),
    SETTINGS("Settings", Icons.Rounded.Settings, "settings_screen"),
}