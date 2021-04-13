package com.example.daggertutorial

import android.util.Log
import javax.inject.Inject

class Car
@Inject constructor(
    var engine: Engine,
    var wheels: Wheels
) {

    private val TAG = "Car"
    fun drive() {
        Log.d(TAG,"dirigindo")
    }

}