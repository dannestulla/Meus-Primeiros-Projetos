package com.example.minhasreceitas.data.database

import androidx.room.*
import com.example.minhasreceitas.data.network.Meal

@Dao
interface ReceitasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToDB(al: ArrayList<Meal>)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun findId(id: String) : List<Meal>

    @Query("DELETE FROM recipes WHERE name= :s")
    fun deleteRow(s: String)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE cuisine=:s")
    suspend fun loadCuisineDB(s: String) :  List<Meal>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun loadMeal(id: String) :  List<Meal>





}