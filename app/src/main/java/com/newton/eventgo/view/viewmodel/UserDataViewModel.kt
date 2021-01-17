package com.newton.eventgo.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.eventgo.repository.UserDataRepository

class UserDataViewModel(private val repository: UserDataRepository) : ViewModel() {

    fun setName(name: String?) = repository.setName(name)
    fun setEmail(email: String?) = repository.setEmail(email)

    val getName get() = repository.getName()
    val getEmail get() = repository.getEmail()

}