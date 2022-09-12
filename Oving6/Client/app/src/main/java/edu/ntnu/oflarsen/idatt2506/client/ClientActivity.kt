package oflarsen.idatt2506.oving6

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ClientActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client)
        val textView = findViewById<TextView>(R.id.received)
        Client(textView).start()
    }
}