package oflarsen.idatt2506.oving6

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val textView: TextView, private val PORT: Int = 12345) {

    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulatoren med
     *
     * ```
     * ui = "noe"
     * ```
     */
    var clients: MutableList<Socket> = mutableListOf()

    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                ui = "Starter Tjener ..."
                Log.i("Connection", "Booting server")
                ServerSocket(PORT).use { serverSocket: ServerSocket ->
                    while (true){
                        val clientSocket = serverSocket.accept()
                        clients.add(clientSocket)
                        startClient(clientSocket)
                        Log.i("Clients", clients.size.toString())
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
                ui = e.message
            }
        }
    }

    private suspend fun startClient(clientSocket: Socket) = coroutineScope{
        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.i("Connection", "Client connected: $clientSocket")
                //send tekst til klienten
                sendToClient(clientSocket,"Welcome!")
                // Hent tekst fra klienten

                readFromClient(clientSocket)

            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Connection", e.message.toString())
            }
        }
    }

    private suspend fun sendToClients(senderSocket: Socket, message: String) = coroutineScope {
        val writer = PrintWriter(senderSocket.getOutputStream(), true)
        clients.forEach {
            if (it != senderSocket)
                CoroutineScope(Dispatchers.IO).launch {
                    sendToClient(it,message)
                }
        }
        writer.println(message)
        Log.i("Message", "Sent to client: $message")
        ui = "Sendte følgende til klienten:\n$message"
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
                    sendToClients(socket, message)
                }
            }
        }
    }

    private suspend fun sendToClient(socket: Socket, message: String) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            val writer = PrintWriter(socket.getOutputStream(), true)
            writer.println(message)
            println("Sent following to clients: $message")
        }
    }

}