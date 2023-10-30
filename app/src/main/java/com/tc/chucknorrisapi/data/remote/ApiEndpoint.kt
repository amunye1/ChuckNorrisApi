package com.tc.chucknorrisapi.data.remote

import com.tc.chucknorrisapi.data.model.AnyModel
import com.tc.chucknorrisapi.data.model.CategoriesModel
import com.tc.chucknorrisapi.data.model.ChuckNorrisItemModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiEndpoint {
    @GET(ApiDetails.JOKES_ENDPOINT)
    suspend fun getRandomJoke(): List <ChuckNorrisItemModel>

    @GET(ApiDetails.CATEGORIES_ENDPOINT)
    suspend fun getRandomJokeInCategory(@Query("category") category: String): List<ChuckNorrisItemModel>


}
