package com.example.noticiasconcursos.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


public interface APIrequests {
    // Titulo com descrição e link, 10 por página
    // https://jobconcursosbr.com/wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self
    // Pegar Imagem
    //https://jobconcursosbr.com/wp-json/wp/v2/media?_fields=media_details.sizes.medium.source_url

    @GET("wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self")
    fun getTextData() : Call<List<TextDataItem>>

    @GET("wp-json/wp/v2/media?_fields=media_details.sizes.medium.source_url")
    fun getImageData() : Call<ImgData>
}