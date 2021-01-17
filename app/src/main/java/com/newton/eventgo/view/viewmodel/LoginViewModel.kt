package com.newton.eventgo.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.eventgo.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login() = repository.login()

    fun logout() = repository.logout()

    fun isLogged(): Boolean = repository.isLogged()
}