package com.example.minhasreceitas.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.minhasreceitas.data.network.Meal

@Dao
interface ReceitasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeList(entity: ArrayList<Meal>)

    @Query("SELECT idMeal FROM recipes")
    suspend fun findId(idMeal : ArrayList<Meal>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewData(mealName : ArrayList<Meal>)


}