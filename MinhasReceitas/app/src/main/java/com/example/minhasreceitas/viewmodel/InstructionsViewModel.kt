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
import javax.inject.Inject

@HiltViewModel
class InstructionsViewModel @Inject constructor(
    private val app: Application,
    private val repository: ReceitasRepository
) : ViewModel() {
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
                        RecipesListViewModel.currentMeal[0].idMeal,
                        RecipesListViewModel.currentMeal[0].strMeal,
                        RecipesListViewModel.currentMeal[0].strMealThumb,
                        mealID[0].strInstructions,
                        RecipesListViewModel.currentMeal[0].strArea.capitalize(),
                        RecipesListViewModel.currentMeal[0].favourite
                    )
                )
                descriptionData.postValue(newData)

            } else {
                getRecipeInstructions(RecipesListViewModel.currentMeal[0].strMeal)
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
                    RecipesListViewModel.currentMeal[0].idMeal,
                    RecipesListViewModel.currentMeal[0].strMeal,
                    RecipesListViewModel.currentMeal[0].strMealThumb,
                    body.strInstructions,
                    RecipesListViewModel.currentMeal[0].strArea.capitalize(),
                    RecipesListViewModel.currentMeal[0].favourite
                )
            )

            //Override old data in DB with new one
            val dbItem = repository.findId(RecipesListViewModel.currentMeal[0].idMeal)
            val currentItem = RecipesListViewModel.currentMeal[0].idMeal
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
                RecipesListViewModel.currentMeal[0].idMeal,
                RecipesListViewModel.currentMeal[0].strMeal,
                RecipesListViewModel.currentMeal[0].strMealThumb,
                RecipesListViewModel.currentMeal[0].strInstructions,
                RecipesListViewModel.currentMeal[0].strArea,
                "1"
            )
        )
        repository.saveToDB(favourite)
        favButtonFromInstructions = true
    }

    suspend fun removeItemFromFavourites() {
        var favourite = ArrayList<Meal>()
        favourite.add(
            Meal(
                RecipesListViewModel.currentMeal[0].idMeal,
                RecipesListViewModel.currentMeal[0].strMeal,
                RecipesListViewModel.currentMeal[0].strMealThumb,
                RecipesListViewModel.currentMeal[0].strInstructions,
                RecipesListViewModel.currentMeal[0].strArea,
                "0"
            )
        )
        repository.saveToDB(favourite)
        favButtonFromInstructions = false
    }

    fun checkIfFavourited() {
        CoroutineScope(IO).launch {
            var favouritesList = repository.loadFavouriteFromDB("1")
            var favMatch = RecipesListViewModel.currentMeal[0].idMeal
            if (favouritesList.toString().contains(favMatch)) {
                isFavourited.postValue(true)
                favButtonFromInstructions = true
            }
        }
    }


}



