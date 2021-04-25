package com.example.noticiasconcursos.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noticiasconcursos.NoticiasViewModelProviderFactory
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.data.NoticiasDao
import com.example.noticiasconcursos.data.NoticiasDatabase
import com.example.noticiasconcursos.features.NoticiasViewModel
import com.example.noticiasconcursos.util.NoticiasRepository

class NoticiasActivity : AppCompatActivity() {
    lateinit var viewModel : NoticiasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val dbCreated = NoticiasDatabase.getDatabase(application)
        dbCreated.NoticiasDao()

        val repository = NoticiasRepository(dbCreated)

        val viewModelProviderFactory = NoticiasViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoticiasViewModel::class.java)

    }


}








