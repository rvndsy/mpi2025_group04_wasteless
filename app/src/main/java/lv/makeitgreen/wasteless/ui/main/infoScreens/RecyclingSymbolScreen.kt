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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@Composable
fun RecyclingSymbolScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            RecyclingSymbolDescriptions(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

data class RecyclingSymbol(
    val code: String,
    val material: String,
    //val icon: ImageVector,
    //val description: String
)

@Composable
fun RecyclingSymbolDescriptions(modifier: Modifier = Modifier) {
    val symbols = listOf(
        RecyclingSymbol("1 – PET / PETE", "Polyethylene Terephthalate"),
        RecyclingSymbol("2 – HDPE", "High-Density Polyethylene"),
        RecyclingSymbol("3 – PVC", "Polyvinyl Chloride"),
        RecyclingSymbol("4 – LDPE", "Low-Density Polyethylene"),
        RecyclingSymbol("5 – PP", "Polypropylene"),
        RecyclingSymbol("6 – PS", "Polystyrene"),
        RecyclingSymbol("7 – Other / O", "Bioplastics, Mixed"),
        RecyclingSymbol("Al", "Aluminium"),
        RecyclingSymbol("Fe / Steel", "Iron, Steel"),
        RecyclingSymbol("GL", "Glass"),
        RecyclingSymbol("PAP", "Paper, Cardboard")
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
                        text = item.code,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = item.material,
                        style = MaterialTheme.typography.bodyMedium
                    )
//                    Text(
//                        text = item.description,
//                        style = MaterialTheme.typography.bodySmall
//                    )
                }
            }
        }
    }
}
