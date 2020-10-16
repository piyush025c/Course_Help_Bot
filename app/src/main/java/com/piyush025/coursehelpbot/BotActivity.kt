package com.piyush025.coursehelpbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.piyush025.coursehelpbot.Constants.OPEN_CLASS
import com.piyush025.coursehelpbot.Constants.OPEN_SEARCH
import com.piyush025.coursehelpbot.Constants.RECEIVE_ID
import com.piyush025.coursehelpbot.Constants.SEND_ID
import kotlinx.android.synthetic.main.activity_bot.*
import kotlinx.coroutines.*

class BotActivity : AppCompatActivity() {

    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bot)

        recyclerView()
        clickEvents()

        customBotMessage("Hello! Today you're speaking with Course Assist Bot, how may I help?")
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()
        if (message.isNotEmpty()) {
            et_message.setText("")
            messagesList.add(Message(message, SEND_ID, timeStamp))
            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)
            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponses.basicResponses(message)
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
                when (response) {
                    OPEN_CLASS -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://meet.google.com/aye-cpga-ksw")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }

        }
    }

    private fun clickEvents() {
        btn_send.setOnClickListener {
            sendMessage()
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun customBotMessage(message: String){
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }

    }

}