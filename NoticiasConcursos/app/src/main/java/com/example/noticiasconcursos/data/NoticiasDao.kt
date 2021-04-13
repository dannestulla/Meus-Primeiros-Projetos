package com.example.noticiasconcursos.data

import androidx.room.*

@Dao
interface NoticiasDao {
    @Query("SELECT * FROM noticias_table")
    fun getAll() : List<NoticiasEntity>


   /* @Query("SELECT title FROM noticias_table")
    fun getTitles() :List<NoticiasEntity>
*/

  /*  @Query("SELECT description FROM noticias_table")
    fun getDescription() :List<NoticiasEntity>

    @Query("SELECT image_url FROM noticias_table")
    fun getImages() :List<NoticiasEntity>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(noticiasEntity: NoticiasEntity)

    @Query("DELETE FROM noticias_table")
    fun deleteAll()
}