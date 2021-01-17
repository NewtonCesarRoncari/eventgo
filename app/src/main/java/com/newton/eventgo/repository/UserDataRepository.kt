package com.newton.eventgo.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_NAME = "NAME"
private const val KEY_EMAIL = "EMAIL"

class UserDataRepository(private val preferences: SharedPreferences) {

    fun setName(name: String?) = data(KEY_NAME, name)
    fun setEmail(email: String?) = data(KEY_EMAIL, email)

    fun getName() = preferences.getString(KEY_NAME, null)
    fun getEmail() = preferences.getString(KEY_EMAIL, null)

    private fun data(key: String, data: String?) {
        preferences.edit {
            putString(key, data)
        }
    }

}