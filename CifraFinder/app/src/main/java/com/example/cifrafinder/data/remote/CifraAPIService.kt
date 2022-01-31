package com.example.cifrafinder.data.remote

import com.example.cifrafinder.CifraConstants
import com.example.cifrafinder.data.remote.model.GoogleJson
import com.example.cifrafinder.data.remote.model.SpotifyJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CifraAPIService {
    @GET(CifraConstants.spotifyEndPoint)
    fun getCurrentlyPlaying(@Header("Authorization Bearer ") myToken: String): Response<SpotifyJson>

    @GET(CifraConstants.googleSearchEndpoint)
    fun getGoogleSearchResult(
        @Query("key") key: String,
        @Query("cx") cx: String,
        @Query("q") q: String
    ): Response<GoogleJson>
}