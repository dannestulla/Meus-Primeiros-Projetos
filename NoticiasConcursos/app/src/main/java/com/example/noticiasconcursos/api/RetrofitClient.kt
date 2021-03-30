package com.example.noticiasconcursos.api

import android.util.Log
import com.example.noticiasconcursos.view.NoticiasFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient : NoticiasFragment() {

    private var i : Int = 0
    var meusTitulos: ArrayList<String> = ArrayList()
    var minhaDescricao: ArrayList<String> = ArrayList()

    fun createClient() {
        val url = "https://jobconcursosbr.com/"
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val jsonRequest = retrofit.create(APIrequests::class.java)
        val call = jsonRequest.getTextData()
        call.enqueue(object : Callback<List<TextDataItem>> {
            override fun onResponse(call: Call<List<TextDataItem>>, response: Response<List<TextDataItem>>) {
            for (item in response.body()!!) {
                meusTitulos.add(response.body()!!.get(i).title.rendered)
                minhaDescricao.add(response.body()!!.get(i).content.rendered)
                i++
                NoticiasFragment().initRecyclerView()

            }}

            override fun onFailure(call: Call<List<TextDataItem>>, t: Throwable) {
                Log.e("Fail", t.toString())
            }

        })
    }




}

