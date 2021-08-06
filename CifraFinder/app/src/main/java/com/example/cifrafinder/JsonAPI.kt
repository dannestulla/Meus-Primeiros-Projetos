package com.example.cifrafinder

import com.example.cifrafinder.json.SearchJson
import com.example.cifrafinder.json.SpotifyJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface JsonAPI {
    @GET("v1/me/player/currently-playing?market=BR")
    fun getCurrentTrack(@Header("Authorization") myToken: String): Call<SpotifyJson>

    @GET("customsearch/v1/siterestrict")
    fun getSearchResult(
        @Query("key") key: String,
        @Query("cx") cx: String,
        @Query("q") q: String
    ): Call<SearchJson>
}


