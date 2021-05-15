package com.example.minhasreceitas.data.network

import android.util.Log
import com.example.minhasreceitas.data.database.ReceitasDao
import com.example.minhasreceitas.data.database.ReceitasDatabase
import retrofit2.Response
import javax.inject.Inject

class ReceitasRepository @Inject constructor(
        private val api: APIrequests,
        db: ReceitasDatabase,
) {
    private val database: ReceitasDao = db.receitasDao()

    //API
    suspend fun getSpecificCuisine(s: String): Response<RecipesList> {
        Log.d("Repository", "API: Getting Specific Cuisine")
        return api.getSpecificCuisineAPI(s)
    }

    suspend fun getRecipe(s: String): Response<DescriptionRecipe> {
        Log.d("Repository", "API: Getting Recipe Description")
        return api.getDescriptionRecipe(s)
    }

    //DATABASE
    suspend fun saveToDB(al: ArrayList<Meal>) {
        Log.d("Repository", "DB: Saving to DB")
        database.saveToDB(al)
    }

    fun deleteRow(s: String) {
        Log.d("Repository", "DB: Deleting Row $s")
        database.deleteRow(s)
    }

    suspend fun findId(id: String): List<Meal> {
        Log.d("Repository", "DB: Finding id $id")
        return database.findId(id)
    }

    suspend fun loadCuisineDB(cuisine: String): List<Meal> {
        Log.d("Repository", "DB: Loading cuisine $cuisine")
        return database.loadCuisineDB(cuisine)
    }

    suspend fun loadFavouriteFromDB(favourite : String) : List<Meal> {
        Log.d("Repository", "DB: Loading favourites list")
        return database.findFavouriteRecipes(favourite)
    }
    /*suspend fun saveFavouriteToDB(favourite : String) : List<Meal> {
        Log.d("Repository", "DB: Loading favourites list")
        return database.saveFavouriteToDB(favourite)
    }*/


}