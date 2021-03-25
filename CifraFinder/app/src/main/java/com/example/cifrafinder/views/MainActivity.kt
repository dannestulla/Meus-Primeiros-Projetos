package com.example.cifrafinder.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.example.cifrafinder.SearchCifraGoogle
import com.example.cifrafinder.databinding.ActivityMainBinding
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

open class MainActivity : AppCompatActivity() {
    val CLIENT_ID = "b2e7d8972f6e4d70af76b860de39cdcc"
    val REDIRECT_URI = "http://localhost:3000/auth/google/callback"
    val REQUEST_CODE = 1337
    lateinit var builder: AuthenticationRequest.Builder
    lateinit var request: AuthenticationRequest
    lateinit var activityMainBinding: ActivityMainBinding



    companion object {
        var myToken: String? = null
        var context: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MultiDex.install(this)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)
        logInSpotify()
        activityMainBinding.button.setOnClickListener { SearchCifraGoogle().getArtistSong() }
        activityMainBinding.imageView.setOnClickListener { logInSpotify() }
        supportActionBar?.hide()
        context = applicationContext
    }

    fun contextReturn(): Context? {
        return context
    }

   fun logInSpotify() {
        builder = AuthenticationRequest.Builder(
                CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI
        ).setScopes(
                arrayOf("user-read-currently-playing")
        )
        request = builder.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> myToken = response.accessToken

                AuthenticationResponse.Type.ERROR -> Log.e("Token Error", response.error)
            }
        }
    }

}
























