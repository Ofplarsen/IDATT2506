package oflarsen.idatt2506.oving6

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class ClientHandler (private val clientSocket: Socket){

    fun start(){
        CoroutineScope(Dispatchers.IO).launch {

            try {

                clientSocket.use { clientSocket: Socket ->

                    Log.i("Connection", "Client connected: $clientSocket")
                    //send tekst til klienten
                    sendToClient(clientSocket, "Welcome!")

                    // Hent tekst fra klienten

                    readFromClient(clientSocket)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
            }
        }
    }

    private fun readFromClient(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        Log.i("Message", "Client said: $message")
    }

    private fun sendToClient(socket: Socket, message: String) {
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(message)
        Log.i("Message", "Sent to client: $message")
    }
}