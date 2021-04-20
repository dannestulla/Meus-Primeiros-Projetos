package com.example.noticiasconcursos.util

import android.app.Application
import com.example.noticiasconcursos.data.NoticiasDatabase
import com.example.noticiasconcursos.data.NoticiasEntity
import com.example.noticiasconcursos.data.ObjectsInstances
import com.example.noticiasconcursos.data.api.FetchedData
import com.example.noticiasconcursos.data.api.ImgDataItem
import com.example.noticiasconcursos.data.api.TextDataItem
import retrofit2.Response

class NoticiasRepository(
    db: NoticiasDatabase
) : Application() {
    val noticiasDao = db.NoticiasDao()

    // API
    suspend fun getTextData(): Response<List<TextDataItem>> {
        return  ObjectsInstances.api.getTextData()
    }

    suspend fun getImgData(): Response<List<ImgDataItem>> {
        return ObjectsInstances.api.getImageData()
    }
    suspend fun getLastData() : Response<List<TextDataItem>> {
        return ObjectsInstances.api.getLastData()
    }
   // DATABASE

    fun deleteAllDb() {
        noticiasDao.deleteAll()
    }
    fun getAllDb() {
        noticiasDao.getAll()
    }
    fun getTitlesDb() :List<String> {
        return noticiasDao.getTitles()
    }
    fun getDescriptionDb():List<String> {
        return noticiasDao.getDescription()
    }
    fun getImagesDb():List<String> {
        return noticiasDao.getImages()
    }
    fun getLinksDb():List<String> {
        return noticiasDao.getLinks()
    }
    fun writeAlltoDb(noticiasEntity : NoticiasEntity) {
        noticiasDao.insertData(noticiasEntity)
    }

}
