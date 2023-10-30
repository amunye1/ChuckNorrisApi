package com.tc.chucknorrisapi.data.repository

import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel

interface Repository {
    suspend fun getRandomJoke(): List<ChuckNorrisItemModel>
    suspend fun getRandomJokeInCategory(category: String): List<ChuckNorrisItemModel>

}