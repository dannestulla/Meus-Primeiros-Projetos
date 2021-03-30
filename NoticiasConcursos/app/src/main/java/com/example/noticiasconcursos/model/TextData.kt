package com.example.noticiasconcursos.api


// related to
// https://jobconcursosbr.com/wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self

class TextData : ArrayList<TextDataItem>()

data class TextDataItem(
        val _links: Links,
        val content: Content,
        var title: Title
    )

data class Links(
    val self: List<Self>
)

data class Content(
    val rendered: String
)

data class Title(
    val rendered: String
) {
    fun getTitle(): String {
        return rendered
    }
}

data class Self(
    val href: String
)