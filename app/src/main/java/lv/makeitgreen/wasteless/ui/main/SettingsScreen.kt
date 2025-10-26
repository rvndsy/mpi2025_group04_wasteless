package lv.makeitgreen.wasteless.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import lv.makeitgreen.wasteless.SAVED_THEME_KEY
import lv.makeitgreen.wasteless.dataStore
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SETTINGS) }

    NavBar(currentDestination, navController) {
        Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThemeSelector()
            }
        }
    }
}

@Composable
fun ThemeSelector() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val themeFlow = context.dataStore.data.map { preferences ->
        preferences[SAVED_THEME_KEY] ?: "System"
    }
    val selectedTheme by themeFlow.collectAsState(initial = "System")

    val themeOptions = listOf("Light", "Dark", "System")

    Row (
        Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 4.dp)
    ) {
        Text("Select app theme")
    }

    themeOptions.forEach { option ->
        Row (
            Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 8.dp)
                .selectable(
                    selected = selectedTheme == option,
                    onClick = {
                        scope.launch {
                            context.dataStore.edit { preferences ->
                                preferences[SAVED_THEME_KEY] = option
                            }
                        }
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton (
                selected = selectedTheme == option,
                onClick = null
            )
            Spacer(Modifier.width(8.dp))
            Text(option)
        }
    }
}