package lv.makeitgreen.wasteless.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lv.makeitgreen.wasteless.navigation.AppDestinations
import lv.makeitgreen.wasteless.navigation.InfoScreenDestinations
import lv.makeitgreen.wasteless.ui.components.NavBar
import lv.makeitgreen.wasteless.ui.icons.MyIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavBar(currentDestination, navController) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            HomeSearchBar(
                searchBarModifier = Modifier.fillMaxWidth(0.95F)
            )
            HomeInfoButtons(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(searchBarModifier: Modifier) {
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it
            },
            onSearch = {
                isSearchActive = false
            },
            active = isSearchActive,
            onActiveChange = {
                isSearchActive = it
            },
            placeholder = {
                Text(
                    text = "Search here",
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (isSearchActive) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close Icon",
                        modifier = Modifier.clickable{
                            if (searchText.isEmpty()) {
                                isSearchActive = false
                            } else {
                                searchText = ""
                            }
                        },
                    )
                }
            },
            modifier = searchBarModifier
        ) {}
    }
}

@Composable
fun HomeInfoButtons(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 114.dp)
            .verticalScroll(rememberScrollState()),
        // verticalArrangement = Arrangement.spacedBy(16.dp), //currently done by top padding within InfoButton
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val buttonHeight = 200.dp
        InfoButton(
            onClick = {
                navController.navigate(InfoScreenDestinations.RECYCLING_SYMBOLS.route)
            },
            buttonHeight = buttonHeight,
            text = "Waste material symbols",
            largeSymbol = MyIcons.OutlineRecycling24,
            iconSize = buttonHeight-64.dp
        )
        InfoButton(
            onClick = {
                navController.navigate(InfoScreenDestinations.WASTE_TYPES.route)
            },
            buttonHeight = buttonHeight,
            text = "About types of waste",
            largeSymbol = MyIcons.OutlineDelete24,
            iconSize = buttonHeight-64.dp
        )
        InfoButton(
            onClick = {
                navController.navigate(InfoScreenDestinations.WASTE_COMPANIES.route)
            },
            buttonHeight = buttonHeight,
            text = "Waste disposal companies",
            largeSymbol = MyIcons.OutlineFactory24,
            iconSize = buttonHeight-64.dp
        )
//        InfoButton(
//            onClick = {},
//            buttonHeight = buttonHeight,
//            text = "(For scrolling)"
//        )
    }
}

@Composable
fun InfoButton(
    onClick: () -> Unit,
    text: String,
    buttonHeight: Dp,
    largeSymbol: ImageVector,
    iconSize: Dp,
    ) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(buttonHeight)
            .padding(top = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                imageVector = largeSymbol,
                contentDescription = text)
            Text(
                fontSize = 24.sp,
                text = text
            )
        }
    }
}
