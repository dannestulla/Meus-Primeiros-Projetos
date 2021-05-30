package com.example.minhasreceitas.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhasreceitas.data.remote.Meal
import com.example.minhasreceitas.data.remote.ReceitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class InstructionsViewModel @Inject constructor(
    val repository: ReceitasRepository
) : ViewModel() {
    companion object {
        lateinit var currentMeal : Meal
    }

    var isLoaded = MutableLiveData<Boolean>()
    var descriptionData = MutableLiveData<ArrayList<Meal>>()
    var favButtonFromInstructions = false
    lateinit var favouriteItem: Meal
    var isFavourited = MutableLiveData<Boolean>()

    fun getInstructions(id: String) {
        CoroutineScope(IO).launch {
            //Checks if exists in DB
            val mealID = repository.findId(id)
            if (mealID[0].strInstructions.isNotBlank()) {
                val newData = ArrayList<Meal>()
                newData.add(
                    Meal(
                        currentMeal.idMeal,
                        currentMeal.strMeal,
                        currentMeal.strMealThumb,
                        mealID[0].strInstructions,
                        currentMeal.strArea.capitalize(Locale.getDefault()),
                        currentMeal.favourite
                    )
                )
                descriptionData.postValue(newData)

            } else {
                getRecipeInstructions(currentMeal.strMeal)
            }
            isLoaded.postValue(true)
        }
    }

    private suspend fun getRecipeInstructions(instructions: String) {
        val response2 = repository.getRecipe(instructions)
        if (response2.isSuccessful) {
            val body = response2.body()!!.meals[0]
            val newData = ArrayList<Meal>()
            newData.add(
                Meal(
                    currentMeal.idMeal,
                    currentMeal.strMeal,
                    currentMeal.strMealThumb,
                    body.strInstructions,
                    currentMeal.strArea.capitalize(Locale.getDefault()),
                    currentMeal.favourite
                )
            )
            //Override old data in DB with new one
            val dbItem = repository.findId(currentMeal.idMeal)
            val currentItem = currentMeal.idMeal
            if (currentItem == dbItem[0].idMeal) {
                repository.deleteRow(currentItem)
                repository.saveToDB(newData)
                descriptionData.postValue(newData)
            }
        }
    }

    suspend fun addToItemToFavourites() {
        val favourite = ArrayList<Meal>()
        favourite.add(
            Meal(
                currentMeal.idMeal,
                currentMeal.strMeal,
                currentMeal.strMealThumb,
                currentMeal.strInstructions,
                currentMeal.strArea,
                "1"
            )
        )
        repository.saveToDB(favourite)
        favButtonFromInstructions = true
    }

    suspend fun removeItemFromFavourites() {
        val favourite = ArrayList<Meal>()
        favourite.add(
            Meal(
                currentMeal.idMeal,
                currentMeal.strMeal,
                currentMeal.strMealThumb,
                currentMeal.strInstructions,
                currentMeal.strArea,
                "0"
            )
        )
        repository.saveToDB(favourite)
        favButtonFromInstructions = false
    }

    fun checkIfFavourited() {
        CoroutineScope(IO).launch {
            val favouritesList = repository.loadFavouriteFromDB("1")
            val favMatch = currentMeal.idMeal
            if (favouritesList.toString().contains(favMatch)) {
                isFavourited.postValue(true)
                favButtonFromInstructions = true
            }
        }
    }
}



