package com.example.noticiasconcursos.data

import com.example.noticiasconcursos.data.api.ImgDataItem
import com.example.noticiasconcursos.data.api.TextDataItem
import retrofit2.Response
import retrofit2.http.GET


public interface APIrequests {

    // https://jobconcursosbr.com/wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self Busca específica

    //https://jobconcursosbr.com/wp-json/wp/v2/media?_fields=media_details.sizes.medium.source_url Imagem
    // /wp-json/wp/v2/posts/?per_page=20
    companion object {
        const val BASE_URL = "https://jobconcursosbr.com/"
    }
    @GET("/wp-json/wp/v2/posts/?per_page=50")
    suspend fun getTextData() : Response<List<TextDataItem>>

    @GET("wp-json/wp/v2/media/?per_page=50")
    suspend fun getImageData() : Response<List<ImgDataItem>>


}