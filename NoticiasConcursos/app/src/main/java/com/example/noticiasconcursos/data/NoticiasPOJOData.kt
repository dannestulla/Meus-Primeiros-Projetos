package com.example.noticiasconcursos.data.api


// related to
// https://jobconcursosbr.com/wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self


class PojoData : ArrayList<TextDataItem>()

data class TextDataItem(
        val content: Content,
        var title: Title
)

data class Content(
    val rendered: String
)

data class Title(
    val rendered: String
)

data class Self(
    val href: String
)
class ImgData : ArrayList<ImgDataItem>()

data class ImgDataItem(
        val media_details: MediaDetails
        )

data class MediaDetails(
        val sizes: Sizes,
)

data class Sizes(
        val medium: Medium,
        val thumbnail: Thumbnail
)

data class Medium(
        val source_url: String,
)

data class Thumbnail(
        val source_url: String,
)

//Conversor de Json Object para Database Object(Room Entity)
data class FetchedData(var myTitles: ArrayList<String>, var myDescription: ArrayList<String>, var myImages: ArrayList<String>)



