package com.example.cifrafinder.data

import com.example.cifrafinder.data.remote.CifraAPIService
import com.example.cifrafinder.data.remote.model.GoogleJson
import retrofit2.Response

class CifraRepository(
    private val cifraAPIService: CifraAPIService
) {
    fun getSpotifyCurrentTrackPlaying(spotifyToken : String) =
        cifraAPIService.getSpotifyCurrentTrackPlaying(spotifyToken)


    fun getGoogleSearchResult(s: String, s1: String, searchInput: String) =
        cifraAPIService.getGoogleSearchResult(s,s1,searchInput)
}