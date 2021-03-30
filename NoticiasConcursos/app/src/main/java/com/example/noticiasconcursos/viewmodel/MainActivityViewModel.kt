package com.example.noticiasconcursos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noticiasconcursos.Repository
import com.example.noticiasconcursos.room.Entity
import com.example.noticiasconcursos.room.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application){
    private val readAllData: LiveData<List<Entity>>
    private val repository : Repository


    init {
        val dao = MyDatabase.getDatabase(application).dao()
        repository = Repository(dao)
        readAllData = repository.readAllData
    }
    fun addUser(entity: Entity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(entity)
        }
    }
    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }
    fun delete(entity: Entity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(entity)
        }
    }
}