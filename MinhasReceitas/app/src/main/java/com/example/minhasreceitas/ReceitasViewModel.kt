package com.example.minhasreceitas

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.data.network.ReceitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceitasViewModel @Inject constructor(
    private val repository : ReceitasRepository
): ViewModel() {
    companion object {
        var mealsList = ArrayList<Meal>()
        var descriptionList = ArrayList<String>()
        var youtubeList  = ArrayList<String>()
        var mealName2 = ArrayList<String>()
        var strArea = ArrayList<String>()
        var mealName = ArrayList<String>()
    }
    var recyclerViewLiveData = MutableLiveData<ArrayList<Meal>>()
    var descriptionData = MutableLiveData<ArrayList<String>>()

   fun getCuisine(type : String) {
       CoroutineScope(IO).launch {
           when (type) {
               type -> getFirstResponse(type)
           }
       }
   }
    suspend fun getRecipe(recipe: String) {
            when (recipe) {
                recipe -> getSecondResponse(recipe)

        }
    }

    private suspend fun getFirstResponse(s: String) {
        val response = repository.getSpecificCuisine(s)
        if (response.isSuccessful) {
            for (items in response.body()!!.meals) {
                mealsList.add(items)
            }
            Log.e("lista", "$mealsList")

            saveToDB(mealsList)
            recyclerViewLiveData.postValue(mealsList)
        }
    }

    private suspend fun getSecondResponse(recipe: String) {
        val response2 = repository.getRecipe(recipe)
        if (response2.isSuccessful) {
            for (items in response2.body()!!.meals) {
                descriptionList.add(items.strInstructions)
                youtubeList.add(items.strYoutube)
                mealName2.add(items.strMeal)
                strArea.add(items.strArea)
            }
            val match = repository.findId(mealName2[0])
            var newArray = ArrayList<Meal>()
            if (mealName[0] == match) {
                newArray.add(Meal("","","",descriptionList[0],descriptionList[0],youtubeList[0],"",strArea[0]))
                          }
                repository.insertNewData(newArray)


            }
        }



    suspend fun saveToDB(mealList: ArrayList<Meal>) {
        repository.saveToDB(mealList)
    }}
