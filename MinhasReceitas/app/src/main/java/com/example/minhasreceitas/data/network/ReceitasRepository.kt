package com.example.minhasreceitas.data.network

import com.example.minhasreceitas.data.database.ReceitasDao
import com.example.minhasreceitas.data.database.ReceitasDatabase
import retrofit2.Response
import javax.inject.Inject

class ReceitasRepository @Inject constructor(
    private val api : APIrequests,
    db : ReceitasDatabase,
){
    private val database: ReceitasDao = db.receitasDao()
    //API R
    suspend fun getSpecificCuisine(type : String) : Response<RecipesList> {
        return api.getSpecificCuisine(type)
    }
    suspend fun getRecipe(recipe : String) : Response<DescriptionRecipe> {
        return api.getDescriptionRecipe(recipe)
    }
    //DB
    suspend fun saveToDB(mealList: ArrayList<Meal>) {
        database.insertRecipeList(mealList)
    }
    suspend fun findId(idMeal : ArrayList<Meal>) {
        database.findId(idMeal)
    }
    suspend fun insertNewData(newData : ArrayList<Meal>) {
        database.insertNewData(newData)
    }

}