package com.example.noticiasconcursos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoticiasEntity::class], version = 3, exportSchema = false)
abstract class NoticiasDatabase : RoomDatabase() {
    abstract fun NoticiasDao(): NoticiasDao

    companion object {
        @Volatile //faz ficar visível para outros Threads
        private var INSTANCE: NoticiasDatabase? = null

        fun getDatabase(context: Context): NoticiasDatabase {
            //confere se já existe uma instancia da db existente
            //se não existir, cria uma nova instance

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //protege essa função de ser executada em outro
            //Thread ao mesmo tempo
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoticiasDatabase::class.java,
                "noticias_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}


