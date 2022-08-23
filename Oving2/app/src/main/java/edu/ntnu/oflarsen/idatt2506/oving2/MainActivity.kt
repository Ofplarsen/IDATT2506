package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generateRandomNumber(view: View?){
        val value = (0..100).random()

        Toast.makeText(this, value.toString(), Toast.LENGTH_LONG).show()
    }
}