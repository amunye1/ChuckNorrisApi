package com.tc.chucknorrisapi.ui.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.chucknorrisapi.ui.navigation.AppBar
import com.tc.chucknorrisapi.ui.navigation.AppNavigation
import com.tc.chucknorrisapi.ui.navigation.DrawerBody
import com.tc.chucknorrisapi.ui.navigation.DrawerHeader
import com.tc.chucknorrisapi.ui.navigation.MenuItem
import com.tc.chucknorrisapi.ui.navigation.Screen

import com.tc.chucknorrisapi.ui.neverendinglist.NeverEndingListScreen
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeScreen
import com.tc.chucknorrisapi.ui.textinput.TextInputScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // Top app bar or any other content
            TopAppBar(
                title = { Text(text = "Chuck Norris App") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer")
                    }
                }
            )
        },
        drawerContent = {
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
            // Content area with padding

        }
    )
}
