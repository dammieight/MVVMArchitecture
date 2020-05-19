package com.example.mvvmarchitecture.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitecture.data.repositories.UserRepository
import com.example.mvvmarchitecture.util.ApiException
import com.example.mvvmarchitecture.util.Coroutines
import com.example.mvvmarchitecture.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var authListener: AuthListener? = null

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    fun getLoggedInUser() = repository.getUser()  //Get user from local database (SqLite)

    fun onLoginButtonClick(v: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Email or password cannot be empty")
            return
        }
        Coroutines.main {
            try {
                val response = repository.userLogin(email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it) // Save user to local database (SqLite)
                    return@main
                }
                response.message?.let { authListener?.onFailure(it) }
            }catch (e: ApiException){
                e.message?.let { authListener?.onFailure(it) }
            }catch (e: NoInternetException){
                e.message?.let { authListener?.onFailure(it) }
            }
        }
    }

    fun onSignUpButtonClick(v: View){
        authListener?.onStarted()
        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name cannot be empty")
            return
        }
        if (email.isNullOrEmpty()){
            authListener?.onFailure("Email cannot be empty")
            return
        }
        if (password.isNullOrEmpty()){
            authListener?.onFailure("Password cannot be empty")
            return
        }
        if (password != confirmPassword){
            authListener?.onFailure("Password did not match")
            return
        }

        Coroutines.main {
            try {
                val response = repository.userSignUp(name!!, email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it) // Save user to local database (SqLite)
                    return@main
                }
                response.message?.let { authListener?.onFailure(it) }
            }catch (e: ApiException){
                e.message?.let { authListener?.onFailure(it) }
            }catch (e: NoInternetException){
                e.message?.let { authListener?.onFailure(it) }
            }
        }
    }

    fun gotoLogin(v: View) {
        v.context.startActivity(Intent(v.context, LoginActivity::class.java))
    }

    fun gotoSignUp(v: View) {
        v.context.startActivity(Intent(v.context, SignUpActivity::class.java))
    }

    override fun onCleared() {
        super.onCleared()
    }
}