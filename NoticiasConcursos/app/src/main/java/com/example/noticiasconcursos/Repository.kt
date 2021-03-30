package com.example.noticiasconcursos

import androidx.lifecycle.LiveData
import com.example.noticiasconcursos.room.Dao
import com.example.noticiasconcursos.room.Entity

class Repository(private val dao: Dao) {

    val readAllData : LiveData<List<Entity>> = dao.getAll()

    fun addData(entity: Entity){
        dao.insert(entity)
    }
    fun deleteAllData() {
        dao.deleteAll()
    }
    fun delete(entity: Entity) {
        dao.delete(entity)
    }
}