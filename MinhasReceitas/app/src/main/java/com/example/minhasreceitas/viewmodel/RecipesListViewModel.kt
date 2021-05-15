package com.example.minhasreceitas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.data.network.ReceitasRepository
import com.example.minhasreceitas.util.ReceitasAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val app: Application,
    private val repository: ReceitasRepository
) : ViewModel() {
    companion object {
        var mealsList = ArrayList<Meal>()
        var currentMeal = ArrayList<Meal>()
        lateinit var cuisineType: String
    }

    lateinit var cuisineType: String
    val mAdapter = ReceitasAdapter()
    var favouritesList = ArrayList<Meal>()
    var favButtonRecipeList = false
    var recyclerViewLiveData: MutableLiveData<ArrayList<Meal>> = MutableLiveData()
    lateinit var dbCuisine: List<Meal>


    fun databaseOrAPI(cuisine: String) {
        CoroutineScope(IO).launch {
            cuisineType = cuisine
            if (dataAvaliableInDB()) {
                loadFromDB()
            } else {
                loadFromApi(cuisine)
            }
        }
    }

    suspend fun loadFromDB() {
        dbCuisine = repository.loadCuisineDB(cuisineType)
        mealsList = dbCuisine as ArrayList<Meal>
        recyclerViewLiveData.postValue(dbCuisine as ArrayList<Meal>)
    }

    private suspend fun loadFromApi(cuisine: String) {
        val response = repository.getSpecificCuisine(cuisine)
        if (response.isSuccessful) {
            mealsList.clear()
            for ((i, items) in response.body()!!.meals.withIndex()) {
                mealsList.add(items)
                mealsList[i].strArea = cuisineType
                Log.e("lista", "$items\n")
            }
            saveToDB(mealsList)
            updateRecyclerView(mealsList)
        }
    }

    suspend fun dataAvaliableInDB(): Boolean {
        return repository.loadCuisineDB(cuisineType).isNotEmpty()
    }

    private fun updateRecyclerView(al: ArrayList<Meal>) {
        recyclerViewLiveData.postValue(al)
    }


    suspend fun saveToDB(mealList: ArrayList<Meal>) {
        repository.saveToDB(mealList)
    }

    suspend fun turnFavouritesListOFF() {
        mAdapter.submitList(emptyList())
        loadFromDB()
        favButtonRecipeList = false
    }

    suspend fun turnFavouritesListON() {
        val favouritesList = repository.loadFavouriteFromDB("1")
        mAdapter.submitList(emptyList())
        mAdapter.submitList(favouritesList)
        favButtonRecipeList = true
    }


}

