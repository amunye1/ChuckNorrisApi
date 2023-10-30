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
class NeverEndingListViewModel @Inject constructor(private val repository: Repository) :ViewModel(){

    private val batchSize = 20
    private val _jokes = MutableStateFlow<List<ChuckNorrisItemModel>>(emptyList())
    val jokes: StateFlow<List<ChuckNorrisItemModel>> = _jokes
    var isLoading = false
    val canFetchNextBatch: Boolean
        get() = !isLoading // You can define this property based on your logic
    init {
        fetchNextBatch()
    }

    fun fetchNextBatch() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            val newJokes = mutableListOf<ChuckNorrisItemModel>()
            for (i in 1..batchSize) {
                try {
                    val response = repository.getRandomJoke()
                    response?.forEach { newJokes.add(it) }
                } catch (exception: Exception) {
                    // Handle the error
                }
            }
            _jokes.value = _jokes.value + newJokes // Concatenate lists
            isLoading = false
        }
    }
}