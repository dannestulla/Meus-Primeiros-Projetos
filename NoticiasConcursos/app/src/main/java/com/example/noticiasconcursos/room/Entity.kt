package com.example.noticiasconcursos.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table") //note_table é usado para a convenção SQL
data class Entity(
        @PrimaryKey(autoGenerate = true) //para cada coluna nova gerada uma nova id será referenciada
        val id: Int,
        val title: String,
        val description: String,
        val priority: Int,
        )

