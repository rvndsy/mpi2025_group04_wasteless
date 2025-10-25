package lv.makeitgreen.wasteless

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {
    HOME("Home", Icons.Rounded.Home, "home_screen"),
    MAP("Map", Icons.Rounded.Place, "map_screen"),
    SEARCH("Search", Icons.Rounded.Search, "search_screen"),
    SCAN("Scanner", Icons.Rounded.Search, "scan_screen"),
    // SETTINGS("Settings", Icons.Rounded.Settings, "settings_screen"),
}
