package com.tc.chucknorrisapi.ui.randomjokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel
import com.tc.chucknorrisapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RandomJokeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // A MutableStateFlow to hold the random joke
    private val _joke = MutableStateFlow<ChuckNorrisItemModel>(ChuckNorrisItemModel())

    // A StateFlow to expose the random joke to the UI
    val jokes: StateFlow<ChuckNorrisItemModel> = _joke

    // Function to retrieve a random Chuck Norris joke
    fun getJoke() {
        viewModelScope.launch {
            // Call the repository to get a random joke
            val response = repository.getRandomJoke()

            // Update the _joke MutableStateFlow with the response or an empty model if null
            _joke.value = response ?: ChuckNorrisItemModel()
        }
    }
}
