package com.example.noticiasconcursos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version =1)
abstract class MyDatabase :RoomDatabase() {

    abstract fun dao() : Dao

    companion object{
        private var INSTANCE : MyDatabase? =null
        fun getDatabase(context : Context): MyDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, MyDatabase::class.java,
                    "my_database").build()
                INSTANCE = instance
                return instance
            }
        }    }
}