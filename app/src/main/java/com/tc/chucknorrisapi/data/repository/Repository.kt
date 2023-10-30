package com.tc.chucknorrisapi.data.repository

import com.tc.chucknorrisapi.data.model.CategoriesModel
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel

interface Repository {
    suspend fun getRandomJoke(): ChuckNorrisItemModel
    suspend fun getRandomJokeInCategory(category: String): CategoriesModel

}