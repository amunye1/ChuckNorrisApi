package com.tc.chucknorrisapi.data.repository

import com.tc.chucknorrisapi.data.model.CategoriesModel
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel
import com.tc.chucknorrisapi.data.remote.ApiEndpoint
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiRequest: ApiEndpoint) :Repository{
    override suspend fun getRandomJoke(): ChuckNorrisItemModel {
        return apiRequest.getRandomJoke()
    }

    override suspend fun getRandomJokeInCategory(category: String): CategoriesModel {
        return apiRequest.getRandomJokeInCategory(category)
    }
}