package edu.ntnu.oflarsen.idatt2506.oving3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showBigPicture(nr: Int){
        startActivity(Intent("edu.ntnu.oflarsen.idatt2506.ShowMovie").putExtra("nr", nr))
    }
}

