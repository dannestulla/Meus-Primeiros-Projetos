package com.example.noticiasconcursos.features

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.noticiasconcursos.data.NoticiasEntity
import com.example.noticiasconcursos.data.api.FetchedData
import com.example.noticiasconcursos.util.NoticiasRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NoticiasViewModel(
    application: Application,
    val noticiasRepository: NoticiasRepository,
) : AndroidViewModel(application) {
    private lateinit var fetchedData: FetchedData
    private var i = 0
    private var results by Delegates.notNull<Int>()
    var isNetworkAvaliable : Boolean = true
    private lateinit var stateStatus : String

    companion object {
        var cardData: ArrayList<CardData> = ArrayList()
        var myTitles: ArrayList<String> = ArrayList()
        var myTitlesDb: ArrayList<String> = ArrayList()
        var myDescription: ArrayList<String> = ArrayList()
        var myImages: ArrayList<String> = ArrayList()
        var result : ArrayList<String> = ArrayList()
        var position: Int? = null
        var myLinks: ArrayList<String> = ArrayList()
        var myLiveData = MutableLiveData<ArrayList<CardData>>()
        var errorText = MutableLiveData<String>()
    }

    init {
        // DB - Pega
        CoroutineScope(IO).launch {
            myTitlesDb = noticiasRepository.getTitlesDb() as ArrayList<String>
            stateStatus = checkStateStatus()
        // Letra Maiúscula para Sim
        // Letra "a" para "db com dados", letra "b" para "internet disponível"
            when (stateStatus) {
                "ab" -> errorText.postValue("É preciso de internet para baixar os dados pela primeira vez")
                "Ab" -> fillRecyclerViewFromDB()
                "aB" -> {fillRecyclerViewFromAPI(transformAPIdata())
                        writeToDatabase()}
                "AB" -> {fillRecyclerViewFromDB()
                        checkForNewPosts() }
            }
        }
    }

    private suspend fun checkForNewPosts() {
        val response = noticiasRepository.getLastData()
        if (response.isSuccessful) {
            val oldValue = noticiasRepository.getTitlesDb()[0]
            val newValue = response.body()!![0].title.rendered
            if (oldValue != newValue){
                fillRecyclerViewFromAPI(transformAPIdata())
            }
        }
    }
    private fun fillRecyclerViewFromDB() {
        myTitles = noticiasRepository.getTitlesDb() as ArrayList<String>
        myImages = noticiasRepository.getImagesDb() as ArrayList<String>
        myLinks = noticiasRepository.getLinksDb() as ArrayList<String>
        myDescription = noticiasRepository.getDescriptionDb() as ArrayList<String>
        cardData.ifEmpty {
        results = minOf(myTitles.size, myImages.size, myDescription.size, myLinks.size)
        i = 0
        while (i < results) {
            cardData.add(CardData(myTitles[i], myDescription[i], myImages[i]))
            i++
        }
        }
        myLiveData.postValue(cardData)}
    fun writeToDatabase() {
        i = 0
        results = minOf(fetchedData.myTitles.size, fetchedData.myImages.size, fetchedData.myDescription.size)
        while (i < results) {
                noticiasRepository.writeAlltoDb(
                    NoticiasEntity(
                        myTitles[i],
                        myDescription[i],
                        myImages[i],
                        myLinks[i]
                    )
                )
            i++
        }
    }

    fun fillRecyclerViewFromAPI(fetchedData: FetchedData) {
        cardData.clear()
        cardData.ifEmpty {
            result = fetchedData.myTitles
            myDescription = fetchedData.myDescription
            myImages = fetchedData.myImages
            results = minOf(result.size, myImages.size, myDescription.size, myLinks.size)
            i = 0
            while (i < results) {
                cardData.add(CardData(result[i].trim(), "", myImages[i]))
                i++
            }
        }
        myLiveData.postValue(cardData) }

    suspend fun transformAPIdata(): FetchedData {

            val response = noticiasRepository.getTextData()
            if (response.isSuccessful) {
                i = 0
                myTitles.clear()
                for (item in response.body()!!) {
                    myTitles.add(response.body()!![i].title.rendered)
                    myDescription.add(response.body()!![i].content.rendered)
                    myLinks.add(response.body()!![i].guid.rendered)
                    i++
                }
            }
            val response2 = noticiasRepository.getImgData()
            if (response2.isSuccessful) {
                i = 0
                myImages.clear()
                for (item in response2.body()!!) {
                    myImages.add(response2.body()!![i].media_details.sizes.medium.source_url)
                    i++
                }
            }
        fetchedData = FetchedData(myTitles, myDescription, myImages, myLinks)
        return FetchedData(myTitles, myDescription, myImages, myLinks)
    }

    fun checkStateStatus() : String{
        val stateStatusBuilder = StringBuilder()
        if (myTitlesDb.isEmpty()) {stateStatusBuilder.append("a")} else {stateStatusBuilder.append("A")}
        if (isNetworkAvaliable) {stateStatusBuilder.append("B")} else {stateStatusBuilder.append("b")}
        stateStatus = stateStatusBuilder.toString()
        Log.e("stateStatus", stateStatus)
        return stateStatus

}}


