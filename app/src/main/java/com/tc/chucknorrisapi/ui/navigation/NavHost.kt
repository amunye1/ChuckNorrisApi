package com.tc.chucknorrisapi.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tc.chucknorrisapi.ui.main.MainScreen
import com.tc.chucknorrisapi.ui.neverendinglist.NeverEndingListScreen
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeScreen
import com.tc.chucknorrisapi.ui.textinput.TextInputScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    // Create a NavController for navigation
    val navController = rememberNavController()

    // Create state for the Scaffold component
    val scaffoldState = rememberScaffoldState()

    // Create a CoroutineScope for handling asynchronous operations
    val scope = rememberCoroutineScope()

    // Create a Scaffold with a top app bar and drawer
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // Top app bar with title and navigation icon
            TopAppBar(
                title = { Text(text = "Chuck Norris App", fontSize = 25.sp) },
                navigationIcon = {
                    // Navigation icon to open the drawer
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer")
                    }
                },
                backgroundColor = Color(0xFF6F7FF7) // Customize the top app bar's background color
            )
        },
        drawerContent = {
            // Drawer content with a list of items for navigation
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "Home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "Random Jokes",
                        title = "Random Jokes",
                        contentDescription = "Go to random joke screen",
                        icon = Icons.Default.Star
                    ),
                    MenuItem(
                        id = "Category",
                        title = "Random Jokes Category",
                        contentDescription = "Go to random jokes category screen",
                        icon = Icons.Default.Create
                    ),
                    MenuItem(
                        id = "List Of Jokes",
                        title = "List Of Jokes",
                        contentDescription = "Go to list of joke screen",
                        icon = Icons.Default.List
                    )
                ),
                onItemClick = { item ->
                    // Handle item clicks and navigate to different screens
                    when (item.id) {
                        "Home" -> navController.navigate(Screen.MainScreen.route)
                        "Random Jokes" -> navController.navigate(Screen.RandomJoke.route)
                        "Category" -> navController.navigate(Screen.TextInput.route)
                        "List Of Jokes" -> navController.navigate(Screen.NeverEndingList.route)
                    }
                }
            )
        },
        content = {it
            // Content area wrapped in a Column
            Column {
                Spacer(modifier = Modifier.height(8.dp)) // Add some spacing
                // NavHost for handling navigation between different screens
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) { MainScreen(navController) }
                    composable(route = Screen.RandomJoke.route) { RandomJokeScreen(navController) }
                    composable(route = Screen.TextInput.route) { TextInputScreen(navController) }
                    composable(route = Screen.NeverEndingList.route) { NeverEndingListScreen(navController) }
                }
            }
        }
    )
}

