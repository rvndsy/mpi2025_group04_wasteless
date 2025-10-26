package lv.makeitgreen.wasteless.ui.main

import android.Manifest
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val context = LocalContext.current
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.MAP) }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Ask for *precise* location permissions
    val locationPermissions = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(Unit) {
        locationPermissions.launchPermissionRequest()
    }

    var latitude by remember { mutableStateOf(57.538) }
    var longitude by remember { mutableStateOf(25.422) }

    // Try to get current (last known) location of the device
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location : Location? ->
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
            }
        }

    // Create the OSM MapView itself
    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(16.0)
            controller.setCenter(GeoPoint(latitude, longitude))
        }
    }

    // Create Overlay - the device current location displayed
    if (locationPermissions.status.isGranted) {
        val overlay = remember { MyLocationNewOverlay(GpsMyLocationProvider(context), mapView) }
        overlay.enableMyLocation()
        overlay.enableFollowLocation()
        DisposableEffect(Unit) {
            mapView.overlays.add(overlay)
            onDispose { mapView.overlays.remove(overlay) }
        }
    }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when {
                locationPermissions.status.isGranted -> {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AndroidView(
                            factory = {
                                mapView
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
//                locationPermissions.status.shouldShowRationale -> {
//                    Text("")
//                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Location permission has been denied. Please enable it in application settings.")
                    }
                }
            }

        }
    }
}
