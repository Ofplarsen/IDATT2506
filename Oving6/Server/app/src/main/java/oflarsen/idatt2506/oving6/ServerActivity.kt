package oflarsen.idatt2506.oving6

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ServerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server)
        val textView = findViewById<TextView>(R.id.traffic)
        Server(textView).start()
    }
}