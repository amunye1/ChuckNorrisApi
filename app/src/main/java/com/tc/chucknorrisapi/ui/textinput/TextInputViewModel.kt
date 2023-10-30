package com.tc.chucknorrisapi.ui.textinput

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
class TextInputViewModel @Inject constructor(private val repository: Repository) :ViewModel(){
    private val _joke = MutableStateFlow<List<ChuckNorrisItemModel>>(emptyList())
    val joke: StateFlow<List<ChuckNorrisItemModel>> = _joke

    fun getJokeInCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRandomJokeInCategory(category)
                _joke.value = response ?: emptyList()
            } catch (exception: Exception) {
                // Handle the error
            }
        }
    }
}