package edu.ntnu.oflarsen.idatt2506.client
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class ClientActivity : Activity() {
    private lateinit var textView: TextView
    private lateinit var client: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client)
        textView = findViewById(R.id.received)
        val edit = findViewById<EditText>(R.id.message)
        client = Client(textView)
        client.start()
    }

    fun sendMessage(v: View){
        Log.i("Info", "Clicked send")
        val message = findViewById<EditText>(R.id.message)
        client.onClickSend(message.text.toString())
    }
}