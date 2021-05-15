package com.example.minhasreceitas.viewmodel

import android.app.Application
import android.content.Context
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val app : Application
) : ViewModel() {
    var fragmentDestination = MutableLiveData<String>()
    lateinit var auth: FirebaseAuth
    var SHARED_PREF = "SharedPref"
    lateinit var password : Editable
    var EMAIL = "email"
    var PASSWORD = "password"

    fun createAccount(email: String, password: String, confpassword: String) {
        if (validadeRegistration(email,password, confpassword)) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(app, "Registration Complete", Toast.LENGTH_LONG).show()
                sendEmailVerification()
                fragmentDestination.postValue("registerToCuisine")
            } else {
                val ex = task.exception.toString()
                Toast.makeText(app, "Create Account Failed, $ex", Toast.LENGTH_LONG).show()
            }
        }
    } else {
        Toast.makeText(app, "Email and password must be over 4 letters and passwords must match", Toast.LENGTH_LONG).show()
        }}

    fun signIn(email : String, password: String) {
        val confpassword = password
        if (validadeRegistration(email,password, confpassword))
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(app, "Login Successfull", Toast.LENGTH_LONG).show()
                    savePref(email, password)
                    fragmentDestination.postValue("loginToCuisine")
                } else {
                    val ex = task.exception.toString()
                    Toast.makeText(app, "SignIn Failed : $ex", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener {
                Toast.makeText(app, "Confirm this account in your email", Toast.LENGTH_LONG).show()
            }
    }
    fun passwordReset(email: String) {
        if (email.isEmpty()) {
            Toast.makeText(app, "Email field is empty", Toast.LENGTH_LONG).show()

        } else {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful || task.isComplete ) {
                    Toast.makeText(app, "Reset Instructions sent to your Email", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(app, "Password Reset Failed", Toast.LENGTH_LONG).show() }}
    }}

    fun loadSavedPref(query : String) : String {
        val sharedPref = app.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        var answer = ""
        when (query) {
            "Email" -> answer = sharedPref.getString(EMAIL, "").toString()
            "Password" -> answer = sharedPref.getString(PASSWORD,"").toString()
        }
        return answer
    }
    fun savePref(email : String, password: String) {
        val sharedPref = app.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        sharedPref.edit().putString(EMAIL, email).apply()
        sharedPref.edit().putString(PASSWORD, password).apply()

    }

    companion object {
    fun validadeRegistration(
        validEmail : String,
        validPassword : String,
        validConfirmedPassword : String) : Boolean {
            if(validEmail.isEmpty() || validPassword.isEmpty() || validConfirmedPassword.isEmpty()) return false
            if(validPassword.length <= 4) return false
            if(validPassword != validConfirmedPassword) return false
        return true
    }}
}


