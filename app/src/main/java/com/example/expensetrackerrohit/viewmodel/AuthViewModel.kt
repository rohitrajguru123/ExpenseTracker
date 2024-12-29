package com.example.expensetrackerrohit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.rpc.context.AttributeContext

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    // Sign in with email and password
    fun signIn(email: String, password: String): LiveData<AttributeContext.Resource<String>> {
        val result = MutableLiveData<AttributeContext.Resource<String>>()
        result.postValue(AttributeContext.Resource.Loading()) // Loading state
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result.postValue(AttributeContext.Resource.Success("Signed In Successfully"))
            } else {
                result.postValue(AttributeContext.Resource.Error(task.exception?.message ?: "Sign In Failed"))
            }
        }
        return result
    }

    // Sign up with email and password
    fun signUp(email: String, password: String): LiveData<AttributeContext.Resource<String>> {
        val result = MutableLiveData<AttributeContext.Resource<String>>()
        result.postValue(AttributeContext.Resource.Loading()) // Loading state
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result.postValue(AttributeContext.Resource.Success("Account Created Successfully"))
            } else {
                result.postValue(AttributeContext.Resource.Error(task.exception?.message ?: "Account Creation Failed"))
            }
        }
        return result
    }
}
