package com.example.mvvmexample.repository

// É onde se guarda dados e item funções para receber ou enviar esses dados.
// Ex: Dados do Retrofit, Room e comandos para ter acesso a esses valores

class DogRepository {
    private val breeds = listOf(
            "German Shepherd",
            "Bulldog",
            "Poodle",
            "Labrador Retriever",
            "Golden Retriever",
            "Beagle",
            "Yorkshire Terrier",
            "Dachshund",
            "Chihuahua"
    )
    fun getRandomBreed(): String {
        return breeds.random()
    }
}
