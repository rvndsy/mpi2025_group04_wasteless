package lv.makeitgreen.wasteless

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import lv.makeitgreen.wasteless.navigation.NavigationMain
import lv.makeitgreen.wasteless.ui.theme.WastelessTheme
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val Context.dataStore by preferencesDataStore(name = "settings")
val SAVED_THEME_KEY = stringPreferencesKey("saved_theme")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // adapt to theme setting changes
            val themeFlow = dataStore.data.map { preferences ->
                preferences[SAVED_THEME_KEY] ?: "System"
            }

            val themeState by themeFlow.collectAsState(initial = "System")
            val isDarkMode = when (themeState) {
                "Dark" -> true
                "Light" -> false
                else -> isSystemInDarkTheme()
            }

            WastelessTheme(
                darkTheme = isDarkMode
            ) {
                val navController = rememberNavController()
                NavigationMain(navController)
            }
        }
    }
}
