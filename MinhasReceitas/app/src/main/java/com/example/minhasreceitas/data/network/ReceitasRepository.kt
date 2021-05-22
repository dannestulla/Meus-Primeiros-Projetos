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
    val TAG = "Repository"
    private val database: ReceitasDao = db.receitasDao()

    //API
    suspend fun getSpecificCuisine(cuisine: String): Response<RecipesList> {
        Log.d(TAG, "API: Getting Specific Cuisine")
        return api.getSpecificCuisineAPI(cuisine)
    }

    suspend fun getRecipe(instructions: String): Response<DescriptionRecipe> {
        Log.d(TAG, "API: Getting Recipe Description")
        return api.getDescriptionRecipe(instructions)
    }

    //DATABASE
    suspend fun saveToDB(mealList: ArrayList<Meal>) {
        Log.d(TAG, "DB: Saving to DB")
        database.saveToDB(mealList)
    }

    fun deleteRow(currentMealName: String) {
        Log.d(TAG, "DB: Deleting Row $currentMealName")
        database.deleteRow(currentMealName)
    }

    suspend fun findId(id: String): List<Meal> {
        Log.d(TAG, "DB: Finding id $id")
        return database.findId(id)
    }

    suspend fun loadCuisineDB(cuisine: String): List<Meal> {
        Log.d(TAG, "DB: Loading cuisine $cuisine")
        return database.loadCuisineDB(cuisine)
    }

    suspend fun loadFavouriteFromDB(favourite: String): List<Meal> {
        Log.d(TAG, "DB: Loading favourites list")
        return database.findFavouriteRecipes(favourite)
    }
}