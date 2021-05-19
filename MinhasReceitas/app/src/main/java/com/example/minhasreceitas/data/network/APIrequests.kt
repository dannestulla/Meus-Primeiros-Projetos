package com.example.minhasreceitas.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIrequests {

    companion object {
        val BASE_URL = "https://themealdb.p.rapidapi.com/"
        const val KEY = "9ecf60d3a4msh169cff80435c289p1601ebjsn0f93f6a89f32"
        const val HEADER = "x-rapidapi-key:"
    }

    @Headers(HEADER + KEY)
    @GET("filter.php")
    suspend fun getSpecificCuisineAPI(@Query("a") type: String): Response<RecipesList>

    //Gets the description for the recipe

    @Headers(HEADER + KEY)
    @GET("search.php")
    suspend fun getDescriptionRecipe(@Query("s") type: String): Response<DescriptionRecipe>
}