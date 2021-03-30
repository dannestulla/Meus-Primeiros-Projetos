package com.example.noticiasconcursos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.noticiasconcursos.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navbar : BottomNavigationView = findViewById(R.id.navigation)
        val noticiasFragment = NoticiasFragment()
        val adicionarFragment = AdicionarFragment()

        changeFragment(noticiasFragment)

        navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.noticias -> changeFragment(noticiasFragment)
                R.id.add -> changeFragment(adicionarFragment)
            }
            true
        }
    }


    private fun changeFragment(fragment: Fragment) { supportFragmentManager.beginTransaction()
        .apply { replace(R.id.fragment_container, fragment)
            .commit()}}}







