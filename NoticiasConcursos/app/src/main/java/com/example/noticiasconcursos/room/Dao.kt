package com.example.noticiasconcursos.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity:Entity)

    @Update
    fun update(entity : Entity)

    @Delete
    fun delete(entity : Entity)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAll() : LiveData<List<Entity>>
}
