package com.example.cifrafinder.presenter.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cifrafinder.data.remote.model.GoogleJson
import com.example.cifrafinder.data.remote.model.SpotifyJson
import com.example.cifrafinder.model.CifraUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class CifraWebViewModel(
    private val cifraUseCase: CifraUseCase
) : ViewModel() {

    private var _searchUrl = MutableLiveData<String>()
    var searchUrl = _searchUrl

    fun getSongUrl() =
        viewModelScope.launch {
            manageResponse(getGoogleSearchResult())

        }

    private fun manageResponse(googleResponse: Response<GoogleJson>) {
        if (googleResponse.isSuccessful) {
            _searchUrl.postValue(googleResponse.body()?.items?.first()?.link)
        } else {
            Log.e(javaClass.simpleName, googleResponse.errorBody().toString())
        }
    }

    private fun getGoogleSearchResult(): Response<GoogleJson> =
        cifraUseCase.getGoogleSearchResult(
            "AIzaSyAqJekjg13XzbdgU3Xw4FCWzQK_84iW4qE",
            "a4cf8ba5446f8f5ac",
            ""
        )
}
