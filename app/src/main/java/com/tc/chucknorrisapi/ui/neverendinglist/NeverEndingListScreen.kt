package com.tc.chucknorrisapi.ui.neverendinglist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeViewModel

@Composable
fun NeverEndingListScreen(navController: NavHostController) {
    // Create a ViewModel instance using Hilt
    val viewModel = hiltViewModel<NeverEndingListViewModel>()

    // Collect the list of Chuck Norris jokes from the ViewModel
    val jokes by viewModel.jokes.collectAsState(emptyList())

    // Create a LazyColumn composable to display the jokes
    LazyColumn(
        content = {
            items(jokes) { joke ->
                // Display each Chuck Norris joke as a Text composable
                Text(
                    text = joke.value ?: "No joke available",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (jokes.isNotEmpty() && viewModel.canFetchNextBatch) {
                // Fetch more jokes when reaching the end of the list
                viewModel.fetchNextBatch()
            }

            if (viewModel.isLoading) {
                // Display a loading indicator when data is being fetched
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )
                }
            }
        }
    )
}


