package com.example.cifrafinder.data

import com.example.cifrafinder.data.remote.CifraAPIService

class CifraRepository(
    private val cifraAPIService: CifraAPIService
) {
    fun getCurrentlyPlaying(spotifyToken : String) =
        cifraAPIService.getCurrentlyPlaying(spotifyToken)


    fun getGoogleSearchResult(s: String, s1: String, searchInput: String) =
        cifraAPIService.getGoogleSearchResult(s,s1,searchInput)
}