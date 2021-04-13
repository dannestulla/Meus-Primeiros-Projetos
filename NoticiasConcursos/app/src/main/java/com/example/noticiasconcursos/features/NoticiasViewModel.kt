package com.example.noticiasconcursos.features

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.example.noticiasconcursos.data.APIrequests
import com.example.noticiasconcursos.data.NoticiasDao
import com.example.noticiasconcursos.data.NoticiasDatabase
import com.example.noticiasconcursos.data.NoticiasEntity
import com.example.noticiasconcursos.data.api.FetchedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoticiasViewModel(application: Application) : AndroidViewModel(application) {

    private var i: Int = 0
    private var myTitles: ArrayList<String> = ArrayList()
    private var fetchedData: FetchedData? = null
    private var results: Int = 0
    private var db : NoticiasDatabase? = null
    lateinit var userDao : NoticiasDao
    val myLiveData : MutableLiveData<ArrayList<CardData>> by lazy {
        MutableLiveData<ArrayList<CardData>>()}


    companion object {
        var cardData: ArrayList<CardData> = ArrayList()
        var myDescription: ArrayList<String> = ArrayList()
        var result = ArrayList<String>()
        var position: Int? = null
        var myImages: ArrayList<String> = ArrayList()
        var nameMap = ArrayList<CardData>()

    }

    init {
        NoticiasDatabase.getDatabase(application.applicationContext)
        CoroutineScope(IO).launch {
            db = Room.databaseBuilder(
                application,
                NoticiasDatabase::class.java, "noticias_database"
            ).build()
            userDao = db?.NoticiasDao()!!
            //userDao!!.getAll()
        }
    }
    fun writeToDatabase() { //cardData.size < i
        i=0
        CoroutineScope(IO).launch {
            while (i < cardData.size ) {
            userDao.insertData(NoticiasEntity(result[i],myDescription[i],myImages[i]))
            i++
        }}


            //data class CardData(var titulo : String, var descricao : String, var id : String, var image : String)
    }
    fun deleteAll() {
        CoroutineScope(IO).launch {
        userDao.deleteAll()}
    }
    fun getFromDatabase() {
        CoroutineScope(IO).launch{
        var data = userDao.getAll()
        nameMap = data.map { CardData(it.title,it.description,it.image_url)} as ArrayList<CardData>;
        cardData = nameMap
        //myImages = data.map{CardData(it.image_url)} as ArrayList<String>
        myLiveData.postValue(nameMap as ArrayList<CardData>?)
        //myImages = nameMap.
        //myImages = userDao.getImages() as ArrayList<String>

        }

        //myLiveData.postValue(data)
    }

    suspend fun fillRecyclerView(): ArrayList<CardData> {

        Log.e("outside Scope", Thread.currentThread().name)
        cardData.ifEmpty {
            fetchedData = createClient()
            Log.e("fillRecView coroutine", Thread.currentThread().name)
            result = fetchedData!!.myTitles
            myDescription = fetchedData!!.myDescription
            myImages = fetchedData!!.myImages
            results = minOf(result.size, myImages.size, myDescription.size)
            Log.e("Size Chosen", results.toString())
            i = 0
            while (i < results) {
                cardData.add(CardData(result[i].trim(), "", myImages[i]))
                i++
            }
        }
        Log.e("cardata", cardData.toString())
        myLiveData.postValue(cardData)
        return cardData
    }


    suspend fun createClient(): FetchedData {
        Log.e("createClient coroutine", Thread.currentThread().name)
        val url = "https://jobconcursosbr.com/"
        val api = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIrequests::class.java)
        Log.e("Nucleo", Thread.currentThread().name)
        val response = api.getTextData()
        if (response.isSuccessful) {
            i = 0
            myTitles.clear()
            for (item in response.body()!!) {
                myTitles.add(response.body()!![i].title.rendered)
                myDescription.add(response.body()!![i].content.rendered)
                i++
            }
        }
        val response2 = api.getImageData()
        if (response2.isSuccessful) {
            i = 0
            myImages.clear()
            for (item in response2.body()!!) {
                myImages.add(response2.body()!![i].media_details.sizes.medium.source_url)
                i++
            }
        }
        return FetchedData(myTitles, myDescription, myImages)
    }
}


