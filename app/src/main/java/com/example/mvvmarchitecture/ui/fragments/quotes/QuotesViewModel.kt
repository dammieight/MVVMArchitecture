package com.example.mvvmarchitecture.ui.fragments.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmarchitecture.data.repositories.QuotesRepository
import com.example.mvvmarchitecture.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
