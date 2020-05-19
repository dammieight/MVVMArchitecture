package com.example.mvvmarchitecture.data.network.responses

import com.example.mvvmarchitecture.data.database.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)