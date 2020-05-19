package com.example.mvvmarchitecture.ui.fragments.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmarchitecture.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    var user = repository.getUser()
}
