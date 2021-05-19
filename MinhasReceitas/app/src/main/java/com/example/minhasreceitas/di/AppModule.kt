package com.example.minhasreceitas.di

import android.app.Application
import androidx.room.Room
import com.example.minhasreceitas.data.database.ReceitasDatabase
import com.example.minhasreceitas.data.network.APIrequests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(APIrequests.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAPIrequests(retrofit: Retrofit): APIrequests =
        retrofit.create(APIrequests::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ReceitasDatabase =
        Room.databaseBuilder(app, ReceitasDatabase::class.java, "receitas")
            .build()
}