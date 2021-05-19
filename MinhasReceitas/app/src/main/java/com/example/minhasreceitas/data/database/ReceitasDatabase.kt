package com.example.minhasreceitas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minhasreceitas.data.network.Meal

@Database(entities = [Meal::class], version = 8, exportSchema = false)
abstract class ReceitasDatabase : RoomDatabase() {

    abstract fun receitasDao(): ReceitasDao
}