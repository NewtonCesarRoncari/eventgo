package com.newton.eventgo.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.eventgo.repository.UserDataRepository

class UserDataViewModel(private val repository: UserDataRepository) : ViewModel() {

    private fun setName(name: String?) = repository.setName(name)
    private fun setEmail(email: String?) = repository.setEmail(email)

    val getName get() = repository.getName()
    val getEmail get() = repository.getEmail()

    fun clearUserData() {
        setName(null)
        setEmail(null)
    }

    fun setUserData(name: String, email: String) {
        setName(name)
        setEmail(email)
    }
}
