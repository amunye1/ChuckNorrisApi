package com.tc.chucknorrisapi.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tc.chucknorrisapi.ui.main.MainScreen
import com.tc.chucknorrisapi.ui.neverendinglist.NeverEndingListScreen
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeScreen
import com.tc.chucknorrisapi.ui.textinput.TextInputScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TextInput.route
    ) {
        composable(route = Screen.MainScreen.route) { MainScreen(navController) }
        composable(route = Screen.RandomJoke.route) { RandomJokeScreen(navController) }
        composable(route = Screen.TextInput.route) { TextInputScreen(navController) }
        composable(route = Screen.NeverEndingList.route) { NeverEndingListScreen(navController) }
    }
}

@Composable
fun SideNavigation(navController: NavHostController) {
    Column {
        Text("Menu Items:")
        Button(
            onClick = { navController.navigate(Screen.RandomJoke.route) },
            content = { Text("Random Joke") }
        )
        Button(
            onClick = { navController.navigate(Screen.TextInput.route) },
            content = { Text("Categories") }
        )
        Button(
            onClick = { navController.navigate(Screen.NeverEndingList.route) },
            content = { Text("Never-Ending List") }
        )
    }
}