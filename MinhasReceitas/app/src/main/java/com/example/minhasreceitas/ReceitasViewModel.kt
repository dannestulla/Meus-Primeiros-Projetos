package com.example.minhasreceitas

import android.util.Log
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
class ReceitasViewModel @Inject constructor(
        private val repository: ReceitasRepository
) : ViewModel() {
    companion object {
        var mealsList = ArrayList<Meal>()
        var currentMeal = ArrayList<Meal>()
        lateinit var cuisineType: String
    }

    var recyclerViewLiveData: MutableLiveData<ArrayList<Meal>> = MutableLiveData()
    var descriptionData = MutableLiveData<ArrayList<Meal>>()

    suspend fun loadFromDB() {
        val dbCuisine = repository.loadCuisineDB(cuisineType)
        mealsList = dbCuisine as ArrayList<Meal>
        recyclerViewLiveData.postValue(dbCuisine)
    }

    suspend fun dataAvaliableInDB(): Boolean {
        return repository.loadCuisineDB(cuisineType).isNotEmpty()
    }

    fun databaseOrAPI(s: String) {
        CoroutineScope(IO).launch {
            cuisineType = s
            if (dataAvaliableInDB()) {
                loadFromDB()
            } else {
                getRecipesList(s)
            }
        }
    }

    suspend fun getInstructions(id: String) {
        //Checks if exists in DB
        val mealID = repository.findId(id)
        if (mealID[0].strInstructions.isNotEmpty()) {
            descriptionData.postValue(repository.loadMeal(mealID[0].toString()) as ArrayList<Meal>)

        } else { getRecipeInstructions(currentMeal[0].strMeal)}

    }

    private suspend fun getRecipesList(s: String) {
        val response = repository.getSpecificCuisine(s)
        if (response.isSuccessful) {
            var i = 0
            for (items in response.body()!!.meals) {
                mealsList.add(items)
                mealsList[i].strArea = cuisineType
                i++
                Log.e("lista", "$items\n")
            }
            saveToDB(mealsList)
            updateRecyclerView(mealsList)

        }
    }

    private fun updateRecyclerView(al: ArrayList<Meal>) {
        recyclerViewLiveData.postValue(al)
    }

    private suspend fun getRecipeInstructions(s: String) {
        val response2 = repository.getRecipe(s)
        if (response2.isSuccessful) {
            val body = response2.body()!!.meals[0]
            val newData = ArrayList<Meal>()
            newData.add(Meal(
                    currentMeal[0].idMeal,
                    currentMeal[0].strMeal,
                    currentMeal[0].strMealThumb,
                    body.strInstructions,
                    body.strYoutube,
                    currentMeal[0].strCategory,
                    currentMeal[0].strArea))

            //Override old data in DB to new one
            val dbItem = repository.findId(currentMeal[0].idMeal)
            val currentItem = currentMeal[0].idMeal
            if ( currentItem == dbItem[0].idMeal) {
                repository.deleteRow(currentItem)
                repository.saveToDB(newData)
                descriptionData.postValue(newData)


            }
        }

    }


    suspend fun saveToDB(mealList: ArrayList<Meal>) {
        repository.saveToDB(mealList)
    }
}
