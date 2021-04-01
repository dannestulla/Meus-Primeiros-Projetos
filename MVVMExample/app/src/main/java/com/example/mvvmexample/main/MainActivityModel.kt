package com.example.mvvmexample.main

import com.example.mvvmexample.repository.DogRepository

class MainActivityModel {
    val dogRepository = DogRepository()

    fun getRandomBreed(): String = dogRepository.getRandomBreed()

}
