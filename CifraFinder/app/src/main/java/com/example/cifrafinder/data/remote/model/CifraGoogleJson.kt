package com.example.cifrafinder.data.remote.model

data class GoogleJson(
    val items: List<VItems>,
)

data class VItems(
    val link: String,
)