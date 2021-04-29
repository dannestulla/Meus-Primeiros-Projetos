package com.example.noticiasconcursos.data.retrofit

import retrofit2.Response
import retrofit2.http.GET


interface APIrequests {

    // ://jobconcursosbr.com/wp-json/wp/v2/media?_fields=media_details.sizes.medium.source_url
    // https://jobconcursosbr.com//wp-json/wp/v2/posts/?per_page=20

    companion object {
        const val BASE_URL = "https://jobconcursosbr.com/"
    }

    @GET("wp-json/wp/v2/posts/?per_page=50&_fields=title,content.rendered,_links.self,guid.rendered")
    suspend fun getTextData(
    ): Response<List<TextDataItem>>

    @GET("wp-json/wp/v2/media/?per_page=50")
    suspend fun getImageData(): Response<List<ImgDataItem>>

    @GET("wp-json/wp/v2/posts/?per_page=1&_fields=title")
    suspend fun getLastData(
    ): Response<List<TextDataItem>>

}