package lv.makeitgreen.wasteless.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.AppDestinations

@Composable
fun NavBar(
    currentDestination: AppDestinations,
    navController: NavController,
    content: @Composable () -> Unit
) {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        }
    ) {
        content
    }
}
