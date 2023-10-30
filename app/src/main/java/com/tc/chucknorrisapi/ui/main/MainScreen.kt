package com.tc.chucknorrisapi.ui.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.chucknorrisapi.ui.navigation.SideNavigation
import com.tc.chucknorrisapi.ui.neverendinglist.NeverEndingListScreen
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeScreen
import com.tc.chucknorrisapi.ui.textinput.TextInputScreen

@Composable
fun MainScreen(navController: NavHostController) {


    Scaffold(
        topBar = {
            // Top app bar or any other content
        },
        content = {it
            Row {
                // Side navigation
                SideNavigation(navController)

                // Content area with padding
                NavHost(
                    navController = navController,
                    startDestination = "randomJoke",
                    modifier = Modifier.padding(16.dp) // Add padding here
                ) {
                    composable("randomJoke") {
                        RandomJokeScreen(navController)
                    }
                    composable("textInput") {
                        TextInputScreen(navController)
                    }
                    composable("neverEndingList") {
                        NeverEndingListScreen(navController)
                    }
                }
            }
        }
    )
}

