package com.example.noticiasconcursos.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticiasDao {
    @Query("SELECT * FROM noticias_table")
    fun getAll(): Flow<List<NoticiasEntity>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT title FROM noticias_table")
    fun getTitles(): List<String>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT image_url FROM noticias_table")
    fun getImages():List<String>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT description FROM noticias_table")
    fun getDescription(): List<String>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT link FROM noticias_table")
    fun getLinks(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(noticiasEntity: NoticiasEntity)

    @Query("DELETE FROM noticias_table")
    fun deleteAll()
}