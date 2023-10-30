package com.tc.chucknorrisapi.ui.textinput

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tc.chucknorrisapi.ui.randomjokes.RandomJokeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputScreen( navController: NavHostController) {
    val viewModel= hiltViewModel<TextInputViewModel>()
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Text field to enter the category
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Enter a category") },
            modifier = Modifier.fillMaxWidth())
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Button to fetch jokes in the entered category
    Button(
        onClick = { viewModel.getJokeInCategory(category) },
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Get Jokes")
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Display the fetched jokes and their categories
    val jokes by viewModel.joke.collectAsState(emptyList())
    if (jokes.isNotEmpty()) {
        jokes.forEach { joke ->
            Text(
                text = "Category: ${joke.categories?.joinToString()}, Joke: ${joke.value}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        // Display a loading indicator or an error message if needed
        CircularProgressIndicator()
    }
}

