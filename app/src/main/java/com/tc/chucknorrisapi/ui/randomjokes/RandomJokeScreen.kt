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
fun RandomJokeScreen( navController: NavHostController) {
    val viewModel= hiltViewModel<RandomJokeViewModel>()
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
        if (!jokes.id.isNullOrEmpty()) {
//            jokes.first().value?.let {
            Text(
                text = jokes.value ?: "", // Assuming the first joke is the random one
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp)
            )
//            }
        } else {
            // Display a loading indicator or an error message if needed
            CircularProgressIndicator()
        }


    }
}
