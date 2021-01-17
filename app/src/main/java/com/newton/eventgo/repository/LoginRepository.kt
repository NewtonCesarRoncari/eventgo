package com.newton.eventgo.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_LOGIN = "LOGIN"

class LoginRepository(private val preferences: SharedPreferences) {

    fun login() = stateLogin(true)

    fun logout() = stateLogin(false)

    private fun stateLogin(stateLogin: Boolean) {
        preferences.edit {
            putBoolean(KEY_LOGIN, stateLogin)
        }
    }

    fun isLogged(): Boolean = preferences.getBoolean(KEY_LOGIN, false)
}