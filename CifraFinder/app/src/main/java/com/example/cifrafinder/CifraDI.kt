package com.example.cifrafinder

import com.example.cifrafinder.data.CifraRepository
import com.example.cifrafinder.data.remote.CifraAPIService
import com.example.cifrafinder.model.CifraUseCase
import com.example.cifrafinder.presenter.viewModels.CifraMenuViewModel
import com.example.cifrafinder.presenter.viewModels.CifraWebViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CifraDI {
    val myModule = module {

        viewModel {
            CifraMenuViewModel(get())
        }

        viewModel {
            CifraWebViewModel(get())
        }

        factory {
            CifraRepository(get())
        }

        factory {
            CifraUseCase(get())
        }

        single {
            get<Retrofit>().create(CifraAPIService::class.java)
        }
        single{
            retrofit(CifraConstants.googleSearchEndPoint)
        }
    }

    private fun retrofit(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}