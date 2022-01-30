package com.example.cifrafinder

import android.app.Application
import com.example.cifrafinder.CifraDI.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CifraApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CifraApplication)
            modules(myModule)
        }
    }
}