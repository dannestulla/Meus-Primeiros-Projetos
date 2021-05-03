package com.example.cifrafinder.views

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cifrafinder.JsonAPI
import com.example.cifrafinder.R
import com.example.cifrafinder.SearchCifraGoogle
import com.example.cifrafinder.json.SearchJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebViewCifra : AppCompatActivity() {
    lateinit var webView : WebView
    private lateinit var refresh : View
    private lateinit var search : String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_cifra)
        refresh = findViewById(R.id.refreshbutton)
        supportActionBar?.hide()
        webView = findViewById(R.id.webview)
        refresh.setOnClickListener { SearchCifraGoogle().getArtistSong() }

        search= intent.getStringExtra("search").toString()
        webView.webViewClient = WebViewClient()
        supportActionBar?.hide()
        searchBuilder()

    }
    fun searchBuilder(){
    SearchCifraGoogle.retrofit = Retrofit.Builder()
            .baseUrl("https://customsearch.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val service = SearchCifraGoogle.retrofit.create(JsonAPI::class.java)
    val call = SearchCifraGoogle.search?.let { service.getSearchResult("AIzaSyAqJekjg13XzbdgU3Xw4FCWzQK_84iW4qE","a4cf8ba5446f8f5ac", it)
    }


    call!!.enqueue(object : Callback<SearchJson> {
        override fun onResponse(call: Call<SearchJson>, response: Response<SearchJson>) {
            try {
                val searchUrl = response.body()!!.items[0].link
                webView.loadUrl(searchUrl)
            } catch (ex : Exception) {
                Toast.makeText(applicationContext, "Música (${SearchCifraGoogle.search}) não encontrada no CifraClub ", Toast.LENGTH_LONG).show()
            }


            Log.e("Endereço", SearchCifraGoogle.urlbar.toString())
        }
        override fun onFailure(call: Call<SearchJson>, t: Throwable) {
            Log.d("error", t.toString())
        }
    })
}


}

