package com.mandeepdhillon.chatee_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.mandeepdhillon.chatee_kotlin.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            startActivity(AuthActivity.newIntent(AuthMode.Login, this))
        }
        binding.registerButton.setOnClickListener {
            startActivity(AuthActivity.newIntent(AuthMode.Register, this))
        }
    }
}

sealed class AuthMode() : Parcelable {
    @Parcelize
    object Login : AuthMode()

    @Parcelize
    object Register : AuthMode()
}