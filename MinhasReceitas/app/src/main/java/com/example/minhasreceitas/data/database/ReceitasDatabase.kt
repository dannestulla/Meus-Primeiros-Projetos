package com.example.minhasreceitas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minhasreceitas.data.network.Meal
import javax.inject.Inject


@Database(entities = [Meal::class],version = 3, exportSchema = false)
abstract class ReceitasDatabase : RoomDatabase() {

    abstract fun receitasDao(): ReceitasDao
}