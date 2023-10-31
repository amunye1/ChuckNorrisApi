package com.tc.chucknorrisapi.ui.neverendinglist

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
class NeverEndingListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    // Batch size for fetching jokes
    private val batchSize = 20

    // MutableStateFlow to hold the list of Chuck Norris jokes
    private val _jokes = MutableStateFlow<List<ChuckNorrisItemModel>>(emptyList())

    // StateFlow to expose the list of Chuck Norris jokes to the UI
    val jokes: StateFlow<List<ChuckNorrisItemModel>> = _jokes

    // Flag to indicate whether data is currently being loaded
    var isLoading = false

    // Property to determine if more data can be fetched
    val canFetchNextBatch: Boolean
        get() = !isLoading // This property can be used to control when to fetch the next batch

    // Constructor initializes the ViewModel
    init {
        fetchNextBatch() // Fetch the first batch of jokes when the ViewModel is created
    }

    // Function to fetch the next batch of Chuck Norris jokes
    fun fetchNextBatch() {
        if (isLoading) return // If data is already being loaded, do not fetch again
        isLoading = true // Set the loading flag to true

        viewModelScope.launch {
            val newJokes = mutableListOf<ChuckNorrisItemModel>()

            // Loop to fetch jokes in batches
            for (i in 1..batchSize) {
                try {
                    val response = repository.getRandomJoke()
                    newJokes.add(response)
                } catch (exception: Exception) {
                    // Handle the error if fetching a joke fails
                }
            }

            // Update the list of jokes by concatenating the new batch
            _jokes.value = _jokes.value + newJokes

            isLoading = false // Reset the loading flag once data is loaded
        }
    }
}
