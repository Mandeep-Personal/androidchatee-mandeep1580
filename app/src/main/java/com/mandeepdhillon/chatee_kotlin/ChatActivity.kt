package com.mandeepdhillon.chatee_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mandeepdhillon.chatee_kotlin.databinding.ActivityChatBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var messagesDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        messagesDB = FirebaseDatabase.getInstance().getReference("Messages")

        // set an onClickListener on the send button to send the user email and message to a save function
        binding.sendMessageButton.setOnClickListener {
            val sender = FirebaseAuth.getInstance().currentUser?.email
            val message = binding.messageInput.text.toString()
            if (sender != null) {
                saveMessage(sender, message)
            }
        }
    }
    private fun saveMessage(sender: String, messageBody: String) {
        val key = messagesDB.push().key
        key ?: return
        val message = Message(sender, messageBody)
        messagesDB.child(key).setValue(message)
    }
}

data class Message(var sender: String = "",var messageBody: String = "")