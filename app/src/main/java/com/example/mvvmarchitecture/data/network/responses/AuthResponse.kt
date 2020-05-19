package com.example.mvvmarchitecture.data.network.responses

import com.example.mvvmarchitecture.data.database.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)