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
    private var clients: MutableList<Socket> = mutableListOf()
    private var traffic: MutableList<String> = mutableListOf()

    private var ui: String? = ""
        set(str) {
            if (str != null) {
                traffic.add(str)
            }
            MainScope().launch { textView.text = createChatString() }
            field = str
        }

    private fun createChatString(): String{
        var str: String = "";
        traffic.forEach { str += it + "\n" }
        return str
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
                ui = "Error: $e.message"
            }
        }
    }

    private suspend fun startClient(clientSocket: Socket) = coroutineScope{
        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.i("Connection", "Client connected: $clientSocket")
                ui = "Client connected: ${clientSocket.port}"
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
        clients.forEach {
            CoroutineScope(Dispatchers.IO).launch {
                if(it.isClosed){
                    clients.remove(it)
                }
                else if (it != senderSocket)
                    sendToClient(it,message)
                else{
                    Log.i("Message", "Found host: " + clients.size.toString())
                }
            }

        }
        ui = "Sent following to clients: $message"
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
                    ui = "Recieved from ${socket.port}: $message"
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