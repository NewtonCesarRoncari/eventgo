package com.newton.eventgo.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.newton.eventgo.R

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.frame_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EventGo)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_navigation)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}