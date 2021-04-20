package com.example.noticiasconcursos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noticiasconcursos.features.NoticiasViewModel
import com.example.noticiasconcursos.util.NoticiasRepository

class NoticiasViewModelProviderFactory(
    val app: Application,
    val newsRepository: NoticiasRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoticiasViewModel(app, newsRepository) as T
    }
}