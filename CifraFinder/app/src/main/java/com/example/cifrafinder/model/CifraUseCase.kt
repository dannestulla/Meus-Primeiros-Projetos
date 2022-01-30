package com.example.cifrafinder.model

import com.example.cifrafinder.data.CifraRepository

class CifraUseCase(
    private val cifraRepository: CifraRepository
) {
    fun getSongAndArtistName(spotifyToken : String) =
        cifraRepository.getSpotifyCurrentTrackPlaying(spotifyToken)


    fun getGoogleSearchResult(s: String, s1: String, searchInput: String) =
        cifraRepository.getGoogleSearchResult(s, s1, searchInput)
}
