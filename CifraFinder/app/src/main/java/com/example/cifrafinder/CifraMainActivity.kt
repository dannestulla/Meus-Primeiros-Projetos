package com.example.cifrafinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.example.cifrafinder.databinding.ActivityMainCifraBinding

open class CifraMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cifra)
    }
}