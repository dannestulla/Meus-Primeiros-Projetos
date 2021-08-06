package com.example.cifrafinder.json

data class SpotifyJson(
    val item: Item,
)

data class Item(
    val artists: List<ArtistX>,
    val name: String,
)

data class ArtistX(
    val name: String,
)


