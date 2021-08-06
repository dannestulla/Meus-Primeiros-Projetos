package com.example.cifrafinder

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.cifrafinder.json.SpotifyJson
import com.example.cifrafinder.presenter.MainActivity
import com.example.cifrafinder.presenter.WebViewCifra
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchCifraGoogle : ViewModel() {

    companion object {
        var urlbar: String? = null
        var search: String? = null
        lateinit var retrofit: Retrofit
    }

    fun getArtistSong() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(JsonAPI::class.java)
        val call = service.getCurrentTrack(" Bearer " + MainActivity.myToken)
        call.enqueue(object : Callback<SpotifyJson> {
            override fun onResponse(call: Call<SpotifyJson>, response: Response<SpotifyJson>) {
                val artistName: String? = response.body()?.item?.name?.trim()
                if (artistName != null) {
                    Log.e("nome da Musica", artistName)
                    val nomeMusica: String = response.body()!!.item.artists[0].name.trim()
                    Log.e("nome do Artista", nomeMusica)
                    search = "$nomeMusica $artistName"
                    val intent = Intent(MainActivity().contextReturn(), WebViewCifra::class.java)
                    intent.putExtra("search", search)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    urlbar = search
                    MainActivity().contextReturn()?.let {
                        ContextCompat.startActivity(it, intent, null)
                    }
                } else {
                    Toast.makeText(
                        MainActivity().contextReturn(),
                        "Uma música deve estar sendo tocada no Spotify para a cifra poder ser procurada",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<SpotifyJson>, t: Throwable) {
                Toast.makeText(
                    MainActivity().contextReturn(),
                    "Erro: Cifra não encontrada",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}



