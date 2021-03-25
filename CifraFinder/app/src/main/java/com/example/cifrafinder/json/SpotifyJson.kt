package com.example.cifrafinder.json

data class SpotifyJson(
    val actions: Actions,
    val context: Context,
    val currently_playing_type: String,
    val is_playing: Boolean,
    val item: Item,
    val progress_ms: Int,
    val timestamp: Long
)

data class Actions(
    val disallows: Disallows
)

data class Context(
    val external_urls: ExternalUrls,
    val href: String,
    val type: String,
    val uri: String
)

data class Item(
    val link: String,
    val album: Album,
    val artists: List<ArtistX>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsXXXX,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val popularity: Int,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)

data class Disallows(
    val pausing: Boolean,
    val skipping_prev: Boolean
)

data class ExternalUrls(
    val spotify: String
)

data class Album(
    val album_type: String,
    val artists: List<Artist>,
    val external_urls: ExternalUrlsXX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
)

data class ArtistX(
    val external_urls: ExternalUrlsXXX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class ExternalIds(
    val isrc: String
)

data class ExternalUrlsXXXX(
    val spotify: String
)

data class Artist(
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class ExternalUrlsXX(
    val spotify: String
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

data class ExternalUrlsX(
    val spotify: String
)

data class ExternalUrlsXXX(
    val spotify: String
)

