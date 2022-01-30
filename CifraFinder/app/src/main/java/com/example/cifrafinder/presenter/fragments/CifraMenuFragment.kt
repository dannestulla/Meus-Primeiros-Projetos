package com.example.cifrafinder.presenter.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cifrafinder.CifraConstants.CLIENT_ID
import com.example.cifrafinder.CifraConstants.REDIRECT_URI
import com.example.cifrafinder.CifraConstants.REQUEST_CODE
import com.example.cifrafinder.databinding.FragmentMenuBinding
import com.example.cifrafinder.presenter.viewModels.CifraMenuViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CifraMenuFragment : Fragment() {

    private var _viewBinding: FragmentMenuBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel : CifraMenuViewModel by viewModel()

    private lateinit var builder: AuthenticationRequest.Builder
    private lateinit var request: AuthenticationRequest

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMenuBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyBinding()
        logInSpotify()
    }

    private fun applyBinding() = with(viewBinding) {
        startSearch.setOnClickListener { viewModel.getSongAndArtistName() }
        spotifyLogin.setOnClickListener { logInSpotify() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun logInSpotify() {
        builder = AuthenticationRequest.Builder(
            CLIENT_ID,
            AuthenticationResponse.Type.TOKEN,
            REDIRECT_URI
        ).setScopes(
            arrayOf("user-read-currently-playing")
        )
        request = builder.build()
        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> viewModel.myToken = response.accessToken
                AuthenticationResponse.Type.ERROR -> Log.e("Token Error", response.error)
                else -> {}
            }
        }
    }
}