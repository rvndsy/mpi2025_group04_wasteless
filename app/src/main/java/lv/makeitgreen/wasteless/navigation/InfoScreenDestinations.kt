package lv.makeitgreen.wasteless.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import lv.makeitgreen.wasteless.ui.icons.MyIcons

// Not part of Navigation Bar
enum class InfoScreenDestinations (
    val label: String,
    val route: String,
) {
    RECYCLING_SYMBOLS("Recycling Symbol Screen", "recycling_symbol_screen"),
    WASTE_COMPANIES("Waste Management Companies", "waste_company_screen"),
    WASTE_TYPES("Waste Types", "waste_types_screen"),
}