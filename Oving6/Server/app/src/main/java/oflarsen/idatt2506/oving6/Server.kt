package oflarsen.idatt2506.oving6

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
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
                        ClientHandler(clientSocket).start()
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

    private fun readFromClient(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        Log.i("Message", "Client said: $message")
        ui = "Klienten sier:\n$message"
    }

    private fun sendToClients(senderSocket: Socket, message: String) {
        val writer = PrintWriter(senderSocket.getOutputStream(), true)
        writer.println(message)
        Log.i("Message", "Sent to client: $message")
        ui = "Sendte følgende til klienten:\n$message"
    }
}