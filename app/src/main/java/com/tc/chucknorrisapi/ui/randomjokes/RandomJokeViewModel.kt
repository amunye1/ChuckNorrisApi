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
class RandomJokeViewModel @Inject constructor(private val repository: Repository) :ViewModel() {
    private val _joke = MutableStateFlow<List<ChuckNorrisItemModel>>(emptyList())
    val jokes: StateFlow<List<ChuckNorrisItemModel>> = _joke



    fun getJoke(){
        viewModelScope.launch {
            val response = repository.getRandomJoke()
            _joke.value= response ?: emptyList()

        }
    }
}