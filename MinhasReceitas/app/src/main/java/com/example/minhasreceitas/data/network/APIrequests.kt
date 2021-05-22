package com.example.minhasreceitas.data.network

import com.example.minhasreceitas.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIrequests {

    companion object {
        val BASE_URL = "https://themealdb.p.rapidapi.com/"
        const val HEADER = "x-rapidapi-key:"
    }

    @Headers(HEADER + BuildConfig.API_KEY)
    @GET("filter.php")
    suspend fun getSpecificCuisineAPI(@Query("a") type: String): Response<RecipesList>

    //Gets the description for the recipe

    @Headers(HEADER + BuildConfig.API_KEY)
    @GET("search.php")
    suspend fun getDescriptionRecipe(@Query("s") type: String): Response<DescriptionRecipe>
}