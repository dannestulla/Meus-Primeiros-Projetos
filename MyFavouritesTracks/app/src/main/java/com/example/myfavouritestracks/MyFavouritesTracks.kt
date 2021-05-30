package com.example.myfavouritestracks

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavouritestracks.databinding.MusicCardBinding
import com.example.myfavouritestracks.databinding.MyFavouritesTracksBinding
import com.example.myfavouritestracks.recyclerview.CardData
import com.example.myfavouritestracks.recyclerview.CustomAdapter
import com.example.myfavouritestracks.retrofit.JsonPlaceHolderApi
import com.example.myfavouritestracks.retrofit.MainPojo
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.regex.Pattern

class MyFavouritesTracks : AppCompatActivity() {
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var builder: AuthenticationRequest.Builder? = null
    private var request: AuthenticationRequest? = null
    private var binding: MyFavouritesTracksBinding? = null
    private var bindingCard: MusicCardBinding? = null
    private var new_item: String? = null
    private var tag_matcher_group: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyFavouritesTracksBinding.inflate(layoutInflater)
        bindingCard = MusicCardBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
        binding!!.button3!!.setOnClickListener { jsonParse() }
        builder = AuthenticationRequest.Builder(
            CLIENT_ID,
            AuthenticationResponse.Type.TOKEN,
            REDIRECT_URI
        )
            .setScopes(arrayOf("user-library-read app-remote-control"))
        request = builder!!.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
        supportActionBar!!.hide()
    }

    protected fun logOff() {
        AuthenticationClient.stopLoginActivity(this, REQUEST_CODE)
        startActivity(Intent(this, LoginMenu::class.java))
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> myToken = response.accessToken
                AuthenticationResponse.Type.ERROR -> Log.e("Token Error", response.error)
                else -> {
                }
            }
        }
    }

    fun openImageBrowser(view: View) {
        val url = view.tag.toString()
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun jsonParse() {
        val url2 = "https://api.spotify.com/"
        val retrofit = Retrofit.Builder().baseUrl(url2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApi.getUserData(" Bearer " + myToken)


        call.enqueue(object : Callback<MainPojo> {
            @RequiresApi(api = Build.VERSION_CODES.N)
            override fun onResponse(call: Call<MainPojo>, response: Response<MainPojo>) {
                val name_tracks = response.body()!!.items
                val al_cardData = ArrayList<CardData>()
                for (items in name_tracks) {
                    val pattern = Pattern.compile(
                        "...........................................................................................................",
                        Pattern.CASE_INSENSITIVE
                    )
                    val matcher = pattern.matcher(items.toString())
                    val matchFound = matcher.find()
                    if (matchFound) {
                        tag_matcher_group = matcher.group()
                        new_item = items.toString().replace(matcher.group(), "").replace("[", "")
                            .replace("]", "")
                    }
                    bindingCard!!.playButton.tag = matcher.group()
                    al_cardData.add(CardData(new_item!!, tag_matcher_group!!))
                }
                mLayoutManager = LinearLayoutManager(applicationContext)
                val mAdapter = CustomAdapter(al_cardData)
                binding!!.recyclerView.layoutManager = mLayoutManager
                binding!!.recyclerView.adapter = mAdapter
                onEnterAnimationComplete()
            }

            override fun onFailure(call: Call<MainPojo>, t: Throwable) {
                println(t)
            }
        })
    }

    companion object {
        private const val CLIENT_ID = "d88d1a6deeea4e18aa2c94b656ff2c62"
        private const val REDIRECT_URI = "http://localhost:3000/auth/google/callback"
        private const val REQUEST_CODE = 1337
        private val TOKEN: String? = null
        private val ERROR: String? = null
        private val mSpotifyAppRemote: SpotifyAppRemote? = null
        private var myToken: String? = null
    }
}