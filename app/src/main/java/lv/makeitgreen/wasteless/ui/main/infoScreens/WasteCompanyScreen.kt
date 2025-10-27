package lv.makeitgreen.wasteless.ui.main.infoScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@Composable
fun WasteCompanyScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WasteCompanyDescriptions(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

data class CompanyInfo(
    val name: String,
    val region: String,
    val uri: String
)

@Composable
fun WasteCompanyDescriptions(modifier: Modifier = Modifier) {
    val symbols = listOf(
        CompanyInfo("SIA \"ZAAO\"", "(Vidzeme, Rīga)", "https://zaao.lv/padomi-atkritumu-skirosanai/"),
        CompanyInfo("SIA \"Jēkabpils pakalpojumi\"", "(Jēkabpils)", "https://www.jekabpils-pakalpojumi.lv/lv/informacija-klientiem/atkritumu-apsaimniekosana/"),
        CompanyInfo("SIA \"Ventspils labiekārtošanas kombināts\"", "(Ventspils)", "https://vlk.lv/atkritumu-skirosana-ventspili/"),
        )

    val uriHandler = LocalUriHandler.current

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
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row {
                    Column(
                        modifier = Modifier.padding(start = 16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = item.region,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Row {
                    TextButton (
                        onClick = {
                            uriHandler.openUri(item.uri)
                        }
                    ) {
                        Text(
                            text = item.uri,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
