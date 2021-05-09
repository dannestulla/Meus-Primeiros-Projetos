package com.example.minhasreceitas.util
import android.util.Log
import com.example.minhasreceitas.R
import com.example.minhasreceitas.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthFirebaseRepository {
    lateinit var auth: FirebaseAuth
    //private val viewModel = AuthViewModel()

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("createAccount", "CreateUserWithEmail:Success")
                val user = auth.currentUser
            } else {
                Log.d("createAccount", task.exception.toString())
                //Toast.makeText(get, "Create Account Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun signIn(email : String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SignInSuccess", task.isSuccessful.toString())
                    //viewModel.fragmentDestination.postValue("toCuisine")
                } else {
                    Log.d("SignInFail", task.exception.toString())
                    //Toast.makeText(app, "SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun sendEmailVerification() {
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
            }
    }
    private fun reload() {

    }
}

