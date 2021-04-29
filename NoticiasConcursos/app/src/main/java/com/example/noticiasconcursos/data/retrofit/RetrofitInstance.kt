package com.example.noticiasconcursos.data.retrofit

import com.example.noticiasconcursos.data.retrofit.APIrequests.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ObjectsInstances {
    companion object {
        val retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val api =
            retrofit.create(APIrequests::class.java)
        }
}


