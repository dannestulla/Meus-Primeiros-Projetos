package com.example.minhasreceitas.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.data.network.ReceitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class InstructionsViewModel @Inject constructor(
    private val app: Application,
    private val repository: ReceitasRepository
) : ViewModel() {
    companion object {
        var currentMeal = ArrayList<Meal>()
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
                        currentMeal[0].idMeal,
                        currentMeal[0].strMeal,
                        currentMeal[0].strMealThumb,
                        mealID[0].strInstructions,
                        currentMeal[0].strArea.capitalize(Locale.getDefault()),
                        currentMeal[0].favourite
                    )
                )
                descriptionData.postValue(newData)

            } else {
                getRecipeInstructions(currentMeal[0].strMeal)
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
                    currentMeal[0].idMeal,
                    currentMeal[0].strMeal,
                    currentMeal[0].strMealThumb,
                    body.strInstructions,
                    currentMeal[0].strArea.capitalize(Locale.getDefault()),
                    currentMeal[0].favourite
                )
            )
            //Override old data in DB with new one
            val dbItem = repository.findId(currentMeal[0].idMeal)
            val currentItem = currentMeal[0].idMeal
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
                currentMeal[0].idMeal,
                currentMeal[0].strMeal,
                currentMeal[0].strMealThumb,
                currentMeal[0].strInstructions,
                currentMeal[0].strArea,
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
                currentMeal[0].idMeal,
                currentMeal[0].strMeal,
                currentMeal[0].strMealThumb,
                currentMeal[0].strInstructions,
                currentMeal[0].strArea,
                "0"
            )
        )
        repository.saveToDB(favourite)
        favButtonFromInstructions = false
    }

    fun checkIfFavourited() {
        CoroutineScope(IO).launch {
            val favouritesList = repository.loadFavouriteFromDB("1")
            val favMatch = currentMeal[0].idMeal
            if (favouritesList.toString().contains(favMatch)) {
                isFavourited.postValue(true)
                favButtonFromInstructions = true
            }
        }
    }


}



