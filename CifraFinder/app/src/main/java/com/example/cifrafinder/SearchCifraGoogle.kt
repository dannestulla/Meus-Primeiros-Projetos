package com.example.cifrafinder

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cifrafinder.json.SpotifyJson
import com.example.cifrafinder.views.MainActivity
import com.example.cifrafinder.views.WebViewCifra
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchCifraGoogle : Activity() {
     private lateinit var refresh : View


    companion object {
        var urlbar : String? = null
        private lateinit var jsonAPI : JsonAPI
        var search : String? = null
        lateinit var retrofit : Retrofit
    }

    fun getArtistSong() {

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(JsonAPI::class.java)
        val call = service.getCurrentTrack(" Bearer " + MainActivity.myToken)
        try {
        call.enqueue(object : Callback<SpotifyJson> {
            override fun onResponse(call: Call<SpotifyJson>, response: Response<SpotifyJson>) {
                val artistName: String? = response.body()?.item?.name?.trim()
                if (artistName != null) {
                    Log.e("nome da Musica", artistName)
                    val nomeMusica: String = response.body()!!.item.artists[0].name.trim()
                    Log.e("nome do Artista", nomeMusica)
                    search = "$nomeMusica $artistName"
                    val intent = Intent(MainActivity().contextReturn(), WebViewCifra::class.java)
                    //searchBuilder()
                    intent.putExtra("search", search)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                    urlbar = search
                    MainActivity().contextReturn()?.let {
                        ContextCompat.startActivity(it, intent, null )
                    }
                } else {
                    Toast.makeText(MainActivity().contextReturn(), "Uma música deve estar sendo tocada no Spotify para a cifra poder ser procurada", Toast.LENGTH_LONG).show()

                }
            }
            override fun onFailure(call: Call<SpotifyJson>, t: Throwable) {

               Toast.makeText(MainActivity().contextReturn(), "Erro: Cifra não encontrada", Toast.LENGTH_LONG).show()
            }
        })
    } catch (ex : Exception) {
        Log.e("error", ex.toString())

        }}


    }
