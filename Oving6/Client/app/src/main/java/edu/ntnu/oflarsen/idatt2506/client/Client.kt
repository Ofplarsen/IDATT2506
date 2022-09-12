package edu.ntnu.oflarsen.idatt2506.client

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
    private val textView: TextView,
    private val SERVER_IP: String = "10.0.2.2",
    private val SERVER_PORT: Int = 12345,
) {
    private var connection: Socket? = null

    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }



    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Kobler til tjener..."
            try {
                connection = Socket(SERVER_IP, SERVER_PORT)
                Log.i("Connection", "Updated connection variable")
                listenForMessage()
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
                ui = e.message
            }
        }
    }

    private fun listenForMessage(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                connection?.let{
                    while(true){
                        val msg = readFromServer(it)
                        if( msg== null){
                            it.close()
                            break
                        }else{
                            Log.e("Connection", msg)
                        }
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
                ui = e.message
            }
        }
    }

    fun onClickSend(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("INfo", "SEnding")
            sendMessage(message)
        }
    }

    private suspend fun sendMessage(message: String) = coroutineScope{
        CoroutineScope(Dispatchers.IO).launch {
            try{
                if(connection == null)
                    Log.i("YIKES", "YIKES")
                connection?.let {
                    Log.i("Message", "Sending message: $message")
                    sendToServer(it, message)
                }
            }catch (e: IOException){
                Log.e("Message",e.message.toString())
            }
        }
    }

    private suspend fun readFromServer(socket: Socket): String? = coroutineScope {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        Log.i("Message", "Server said: $message")
        ui = "Melding fra tjeneren:\n$message"
        return@coroutineScope message
    }

    private suspend fun sendToServer(socket: Socket, message: String) = coroutineScope{
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(message)
        Log.i("Message", "Sent to server: $message")
        ui = "Sendte følgende til tjeneren: \n\"$message\""
    }

}