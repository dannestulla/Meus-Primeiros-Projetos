package com.example.minhasreceitas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minhasreceitas.data.remote.Meal

@Database(entities = [Meal::class], version = 8, exportSchema = false)
abstract class ReceitasDatabase : RoomDatabase() {

    abstract fun receitasDao(): ReceitasDao
}