package com.example.mvvmarchitecture.data.repositories

import com.example.mvvmarchitecture.data.database.AppDatabase
import com.example.mvvmarchitecture.data.database.entities.User
import com.example.mvvmarchitecture.data.network.ApiServices
import com.example.mvvmarchitecture.data.network.SmartApiRequest
import com.example.mvvmarchitecture.data.network.responses.AuthResponse

class UserRepository(
    private val apiServices: ApiServices,
    private val db: AppDatabase
) : SmartApiRequest(){
    suspend fun userLogin(email: String, password: String): AuthResponse{
        return apiRequest { apiServices.login(email, password) }
    }

    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse{
        return apiRequest { apiServices.signUp(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().updateOrInsert(user)

    fun getUser() = db.getUserDao().getUser()
}