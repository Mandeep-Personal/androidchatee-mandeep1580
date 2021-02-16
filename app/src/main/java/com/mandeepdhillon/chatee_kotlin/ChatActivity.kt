package com.mandeepdhillon.chatee_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mandeepdhillon.chatee_kotlin.databinding.ActivityChatBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textLabel.text = FirebaseAuth.getInstance().currentUser?.email
    }
}