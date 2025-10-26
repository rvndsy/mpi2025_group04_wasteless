package lv.makeitgreen.wasteless.navigation

// Not part of Navigation Bar
enum class InfoScreenDestinations (
    val label: String,
    val route: String,
) {
    RECYCLING_SYMBOLS("Recycling Symbol Screen", "recycling_symbol_screen"),
    WASTE_COMPANIES("Waste Management Companies", "waste_company_screen"),
    WASTE_TYPES("Waste Types", "waste_types_screen"),
}