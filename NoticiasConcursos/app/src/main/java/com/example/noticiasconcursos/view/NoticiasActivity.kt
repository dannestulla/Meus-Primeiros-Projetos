package com.example.noticiasconcursos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.noticiasconcursos.R


class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val noticiasFragment = NoticiasFragment()

        changeFragment(noticiasFragment)
    }


    fun changeFragment(fragment: Fragment) { supportFragmentManager.beginTransaction()
        .apply {
            replace(R.id.fragment_container, fragment
            ).addToBackStack(null)
            .commit()}
       }




}








