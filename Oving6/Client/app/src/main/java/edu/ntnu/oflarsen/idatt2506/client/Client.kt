package edu.ntnu.oflarsen.idatt2506.client

import android.util.Log
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
    private lateinit var connection: Socket

    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }



    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Kobler til tjener..."
            try {

                Socket(SERVER_IP, SERVER_PORT).use { socket: Socket ->
                    connection = socket
                    ui = "Koblet til tjener:\n$socket"
                    Log.i("Connection", "Connected to server: $socket")


                    readFromServer(socket)

                    sendToServer(socket, "Heisann Tjener! Hyggelig å hilse på deg")
                }
                Log.i("Connection", "Updated connection variable")
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
                ui = e.message
            }
        }
    }

    fun sendMessage(message: String){

        CoroutineScope(Dispatchers.IO).launch {
            try{
                Socket(SERVER_IP, SERVER_PORT).use { socket: Socket ->
                    Log.i("Message", "Sending message: $message")
                    sendToServer(socket, message)
                }
            }catch (e: IOException){
                Log.e("Message",e.message.toString())
            }
        }
    }

    private fun readFromServer(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        Log.i("Message", "Server said: $message")
        ui = "Melding fra tjeneren:\n$message"
    }

    private fun sendToServer(socket: Socket, message: String) {
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(message)
        Log.i("Message", "Sent to server: $message")
        ui = "Sendte følgende til tjeneren: \n\"$message\""
    }
}