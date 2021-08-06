package com.example.cifrafinder.json

data class SearchJson(
    val items: List<VItems>,
)

data class VItems(
    val link: String,
)

