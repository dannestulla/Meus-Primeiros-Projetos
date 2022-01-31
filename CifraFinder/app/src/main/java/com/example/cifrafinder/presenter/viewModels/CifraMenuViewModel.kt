package com.example.cifrafinder.presenter.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cifrafinder.data.remote.model.SpotifyJson
import com.example.cifrafinder.model.CifraUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class CifraMenuViewModel(
    private val cifraUseCase: CifraUseCase
) : ViewModel() {

    private var _spotifyToken = MutableLiveData<String>()
    var spotifyToken : LiveData<String> = _spotifyToken

    private var _currentlyPlaying = MutableLiveData<String>()
    var currentlyPlaying : LiveData<String> = _currentlyPlaying

    fun getCurrentlyPlaying() {
        viewModelScope.launch {
            _spotifyToken.value?.let {
                manageSpotifyResponse(cifraUseCase.getCurrentlyPlaying(it))
            }
        }
    }

    private fun manageSpotifyResponse(spotifyResponse: Response<SpotifyJson>) {
        if (spotifyResponse.isSuccessful) {
            val stringToSearch = with (spotifyResponse.body()?.item) {
                "${this?.name?.first()} ${this?.artists?.first()}"
            }
            _currentlyPlaying.postValue(stringToSearch)
        } else {
            Log.e(javaClass.simpleName, spotifyResponse.errorBody().toString())
            _currentlyPlaying.postValue("")
        }
    }

    fun setSpotifyToken(accessToken: String) {
        _spotifyToken.postValue(accessToken)
    }
}
