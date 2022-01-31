package com.example.cifrafinder.presenter.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cifrafinder.CifraConstants
import com.example.cifrafinder.CifraConstants.spotifyClientId
import com.example.cifrafinder.CifraConstants.googleRedirectUri
import com.example.cifrafinder.CifraConstants.REQUEST_CODE
import com.example.cifrafinder.R
import com.example.cifrafinder.databinding.FragmentMenuBinding
import com.example.cifrafinder.presenter.viewModels.CifraMenuViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CifraMenuFragment : Fragment() {

    private var _viewBinding: FragmentMenuBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: CifraMenuViewModel by viewModel()

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
        setObservers()
        logInSpotify()
    }

    private fun setObservers() {
        viewModel.currentlyPlaying.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                findNavController().navigate(R.id.action_cifraMenuFragment_to_cifraWebFragment, createBundle(it))
            }
        })
    }

    private fun createBundle(searchText: String): Bundle {
        val bundle = Bundle()
        bundle.putString(
            CifraConstants.searchText, searchText)
        return bundle
    }

    private fun applyBinding() = with(viewBinding) {
        startSearchButton.setOnClickListener {
            if (viewModel.spotifyToken.value?.isNotEmpty() == true) {
                viewModel.getCurrentlyPlaying()
            }
            spotifyLogin.setOnClickListener { logInSpotify() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun logInSpotify() {
        val request = AuthenticationRequest.Builder(
            spotifyClientId,
            AuthenticationResponse.Type.TOKEN,
            googleRedirectUri
        ).setScopes(
            arrayOf("user-read-currently-playing")
        ).build()
        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> viewModel.setSpotifyToken(response.accessToken)
                AuthenticationResponse.Type.ERROR -> Log.e(javaClass.simpleName, response.error)
                else -> {}
            }
        }
    }
}