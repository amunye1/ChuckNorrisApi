package com.tc.chucknorrisapi.ui.textinput

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.chucknorrisapi.data.model.CategoriesModel
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel
import com.tc.chucknorrisapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextInputViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // MutableStateFlow to hold the fetched joke in a specific category
    private val _joke = MutableStateFlow<CategoriesModel>(CategoriesModel())

    // Expose the joke as a StateFlow for observation
    val joke: StateFlow<CategoriesModel> = _joke

    // Function to fetch a joke based on a specific category
    fun getJokeInCategory(category: String) {
        viewModelScope.launch {
            try {
                // Call the repository to retrieve a random joke in the specified category
                val response = repository.getRandomJokeInCategory(category)
                // Update the StateFlow with the fetched joke or an empty one if the response is null
                _joke.value = response ?: CategoriesModel()
            } catch (exception: Exception) {
                // Handle the error, such as network issues or API errors
                Log.d("ViewModel Error", exception.message.toString())
            }
        }
    }
}
