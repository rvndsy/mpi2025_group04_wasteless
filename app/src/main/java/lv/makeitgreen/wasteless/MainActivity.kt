package lv.makeitgreen.wasteless

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import lv.makeitgreen.wasteless.navigation.NavigationMain
import lv.makeitgreen.wasteless.ui.theme.WastelessTheme

import org.osmdroid.config.Configuration.*

class MainActivity : ComponentActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        enableEdgeToEdge()
        setContent {
            WastelessTheme {
                val navController = rememberNavController()
                NavigationMain(navController)
            }
        }
    }
}
