package lv.makeitgreen.wasteless.ui.main.infoScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@Composable
fun WasteTypesScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WasteTypesDescriptions(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

data class WasteType(
    val type: String,
    val description: String
)

@Composable
fun WasteTypesDescriptions(modifier: Modifier = Modifier) {
    val symbols = listOf(
        WasteType("Biological (BIO)", "Food and garden waste including food waste in paper bags that can decompose naturally. Any type of liquid (oil, sauces, etc.) is not allowed."),
        WasteType("Paper", "Paper, books, paper journals, newspapers, cardboard boxes, cardboard liquid containers. No paper towers, single-use paper dishes, plastic food packaging."),
        WasteType("Plastics", "Allowed: PET, PP, HDPE, PS, LDPE.\nNot allowed: PS, PVC, OTHER, oil bottles, most food packaging, styrofoam, single use plastics."),
        WasteType("Metals", "Clean metal food and drink containers, metal lids, pure metal everyday items. Sharp and mixed-material objects are NOT allowed."),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(symbols) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = item.type,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
