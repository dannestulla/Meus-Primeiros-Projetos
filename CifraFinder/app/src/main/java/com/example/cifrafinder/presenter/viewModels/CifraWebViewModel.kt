package com.example.cifrafinder.presenter.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cifrafinder.CifraConstants
import com.example.cifrafinder.data.remote.model.GoogleJson
import com.example.cifrafinder.model.CifraUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class CifraWebViewModel(
    private val cifraUseCase: CifraUseCase
) : ViewModel() {

    private var _searchUrl = MutableLiveData<String>()
    var searchUrl = _searchUrl

    fun getSongUrl(artistAndSong : String) =
        viewModelScope.launch {
            manageGoogleResponse(getGoogleSearchResult(CifraConstants.googleApiKey1, CifraConstants.googleApiKey2, artistAndSong))
        }

    private fun manageGoogleResponse(googleResponse: Response<GoogleJson>) {
        if (googleResponse.isSuccessful) {
            _searchUrl.postValue(googleResponse.body()?.items?.first()?.link)
        } else {
            Log.e(javaClass.simpleName, googleResponse.errorBody().toString())
        }
    }

    private fun getGoogleSearchResult(apiKey: String, apiKey2: String, searchText: String): Response<GoogleJson> =
        cifraUseCase.getGoogleSearchResult(
            apiKey,
            apiKey2,
            searchText
        )
}
