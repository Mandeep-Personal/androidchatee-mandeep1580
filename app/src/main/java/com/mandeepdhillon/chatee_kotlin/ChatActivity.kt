package com.mandeepdhillon.chatee_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mandeepdhillon.chatee_kotlin.databinding.ActivityChatBinding
import com.mandeepdhillon.chatee_kotlin.databinding.ItemMessageBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var messagesDB: DatabaseReference

    val messages = listOf(
        Message("someguy@example.com", "Oh hai! Blah blah blah blah blahblahblah."),
        Message("someotherguy@example.com", "Yaya, blee blee bleeee.")
    )

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

        // single column layout
        binding.messageList.layoutManager = LinearLayoutManager(this)
        // pass messages to the adapter
        binding.messageList.adapter = MessagesAdapter(messages)
    }

    private fun saveMessage(sender: String, messageBody: String) {
        val key = messagesDB.push().key
        key ?: return
        val message = Message(sender, messageBody)
        messagesDB.child(key).setValue(message)
    }
}

private class MessagesAdapter(val messages: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class MessagesViewHolder(var binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        // need to explicitly cast the RecyclerView.ViewHolder as a MessagesViewHolder
        val messageHolder = holder as MessagesViewHolder
        // now we have access to each view element of the ViewHolder by id
        messageHolder.binding.senderLabel.text =  message.sender
        messageHolder.binding.messageBodyLabel.text = message.messageBody
    }

    override fun getItemCount(): Int {
        return messages.count()
    }
}


data class Message(var sender: String,var messageBody: String)