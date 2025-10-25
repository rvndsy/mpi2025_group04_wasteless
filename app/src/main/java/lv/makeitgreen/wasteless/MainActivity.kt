package lv.makeitgreen.wasteless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import lv.makeitgreen.wasteless.navigation.NavigationMain
import lv.makeitgreen.wasteless.ui.theme.WastelessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WastelessTheme {
                val navController = rememberNavController()
                NavigationMain(navController)
            }
        }
    }
}
