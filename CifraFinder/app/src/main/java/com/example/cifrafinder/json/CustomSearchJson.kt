package com.example.cifrafinder.json

data class SearchJson(
    val context: Context,
    val items: List<VItems>,
    val kind: String,
    val queries: Queries,
    val searchInformation: SearchInformation,
    val url: Url
)

data class VItems(
    val cacheId: String,
    val displayLink: String,
    val formattedUrl: String,
    val htmlFormattedUrl: String,
    val htmlSnippet: String,
    val htmlTitle: String,
    val kind: String,
    val link: String,
    val snippet: String,
    val title: String
)

data class Queries(
    val nextPage: List<NextPage>,
    val request: List<Request>
)

data class SearchInformation(
    val formattedSearchTime: String,
    val formattedTotalResults: String,
    val searchTime: Double,
    val totalResults: String
)

data class Url(
    val template: String,
    val type: String
)



data class CseImage(
    val src: String
)

data class CseThumbnail(
    val height: String,
    val src: String,
    val width: String
)



data class NextPage(
    val count: Int,
    val cx: String,
    val disableCnTwTranslation: String,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)

data class Request(
    val count: Int,
    val cx: String,
    val disableCnTwTranslation: String,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)