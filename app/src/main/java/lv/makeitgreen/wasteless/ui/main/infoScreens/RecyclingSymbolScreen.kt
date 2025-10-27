package lv.makeitgreen.wasteless.ui.main.infoScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.R
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar
import lv.makeitgreen.wasteless.ui.icons.RecyclingCodes

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
    val description: String,
    val icon: Painter,
)

@Composable
fun RecyclingSymbolDescriptions(modifier: Modifier = Modifier) {
    val symbols = listOf(
        RecyclingSymbol("1 – PET / PETE", "Polyethylene Terephthalate", rememberVectorPainter(RecyclingCodes.PlasticRecyclingCode01PETIcon)),
        RecyclingSymbol("2 – HDPE", "High-Density Polyethylene", rememberVectorPainter(RecyclingCodes.PlasticRecyclingCode02PEHDIcon)),
        RecyclingSymbol("3 – PVC", "Polyvinyl Chloride", painterResource(R.drawable.symbol_resin_code_03_pvc)),
        RecyclingSymbol("4 – LDPE", "Low-Density Polyethylene", rememberVectorPainter(RecyclingCodes.PlasticRecyclingCode04PELDIcon)),
        RecyclingSymbol("5 – PP", "Polypropylene", rememberVectorPainter(RecyclingCodes.PlasticRecyclingCode05PPIcon)),
        RecyclingSymbol("6 – PS", "Polystyrene", rememberVectorPainter(RecyclingCodes.PlasticRecyclingCode06PSIcon)),
        RecyclingSymbol("7 – Other / O", "Bioplastics, Mixed", painterResource(R.drawable.plastic_recyc_07)),
        RecyclingSymbol("Al", "Aluminium", painterResource(R.drawable.alu_recycling_code)),
        RecyclingSymbol("Fe / Steel", "Iron, Steel", painterResource(R.drawable.recycling_code_40)),
        RecyclingSymbol("GL", "Glass", painterResource(R.drawable.recycling_code_70)),
        RecyclingSymbol("PAP", "Paper, Cardboard", rememberVectorPainter(RecyclingCodes.RecyclingCodesPaper20PAPIcon))
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
                Row {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = item.icon,
                            contentDescription = item.description,
                            modifier = Modifier.width(64.dp)
                        )
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.code,
                            style = MaterialTheme.typography.titleLarge
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
}
