package com.example.mvvmexample.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val model = MainActivityModel()
    val dogBreedLiveData = MutableLiveData<String>()

    init {
        getRandomDogBreed()
    }

    fun getRandomDogBreed() {
        val dogBreed = model.getRandomBreed()
        dogBreedLiveData.postValue(dogBreed)
    }
}