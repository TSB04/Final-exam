package com.example.finalexamapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toast example
        Toast.makeText(this, getString(R.string.welcome_message), Toast.LENGTH_LONG).show()

        // Snackbar example
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, getString(R.string.snackbar_message), Snackbar.LENGTH_SHORT).show()
    }
}