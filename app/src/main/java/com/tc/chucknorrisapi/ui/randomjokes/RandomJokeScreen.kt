package com.tc.chucknorrisapi.ui.randomjokes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel

@Composable
fun RandomJokeScreen(navController: NavHostController) {
    // Initialize the ViewModel for the Random Joke screen
    val viewModel = hiltViewModel<RandomJokeViewModel>()

    // Collect the random joke as a State
    val jokes by viewModel.jokes.collectAsState(ChuckNorrisItemModel())

    // Trigger the retrieval of a random joke when the screen is displayed
    DisposableEffect(Unit) {
        viewModel.getJoke()
        onDispose { /* Cleanup if needed */ }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Check if a random joke is available
        if (!jokes.id.isNullOrEmpty()) {
            Text(
                text = jokes.value ?: "", // Display the value of the random joke
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            // If no joke is available, display a loading indicator or an error message
            CircularProgressIndicator()
        }
    }
}

