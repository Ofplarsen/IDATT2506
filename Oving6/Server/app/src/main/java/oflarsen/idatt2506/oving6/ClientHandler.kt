package oflarsen.idatt2506.oving6

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class ClientHandler (val clientSocket: Socket){
    suspend fun start() = coroutineScope{
        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.i("Connection", "Client connected: $clientSocket")
                //send tekst til klienten
                sendToClient("Welcome!")
                // Hent tekst fra klienten

                readFromClient(clientSocket)

            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
            }
        }
    }



    private suspend fun readFromClient(socket: Socket) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                val message = reader.readLine()

                if (message == null) {

                    socket.close()
                    break
                } else {

                    println("Client says: $message")
                }
            }
        }
    }

    suspend fun sendToClient(message: String) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            val writer = PrintWriter(clientSocket.getOutputStream(), true)
            writer.println(message)
            println("Sent following to clients: $message")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClientHandler

        if (clientSocket != other.clientSocket) return false

        return true
    }

    override fun hashCode(): Int {
        return clientSocket.hashCode()
    }


}