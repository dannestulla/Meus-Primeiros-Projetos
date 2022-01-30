package com.example.cifrafinder.presenter.viewModels

import android.util.Log
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
    var myToken: String? = null

    private var currentlyPlaying = MutableLiveData<String>()

    fun getSongAndArtistName(spotifyToken : String) {
        viewModelScope.launch {
            manageResponse(cifraUseCase.getSongAndArtistName(spotifyToken))
        }
    }

    private fun manageResponse(spotifyResponse: Response<SpotifyJson>) {
        return if (spotifyResponse.isSuccessful) {
            val stringToSearch = with (spotifyResponse.body()?.item) {
                "${this?.name?.first()} ${this?.artists?.first()}"
            }
            currentlyPlaying.postValue(stringToSearch)
        } else {
            Log.e(javaClass.simpleName, spotifyResponse.errorBody().toString())
            currentlyPlaying.postValue("")
        }
    }
}
