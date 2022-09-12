package edu.ntnu.oflarsen.idatt2506.client
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class ClientActivity : Activity() {
    private lateinit var textView: TextView
    private lateinit var client: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client)
        textView = findViewById<TextView>(R.id.received)
        client = Client(textView)
        Client(textView).start()
    }

    fun sendMessage(v: View){
        val message = findViewById<EditText>(R.id.message)
        client.sendMessage(message.toString())
    }
}