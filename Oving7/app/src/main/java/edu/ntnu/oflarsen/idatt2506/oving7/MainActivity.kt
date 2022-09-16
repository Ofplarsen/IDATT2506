package edu.ntnu.oflarsen.idatt2506.oving7

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.ntnu.oflarsen.idatt2506.oving7.databinding.MyLayoutBinding

class MainActivity : Activity() {

    private lateinit var layout: MyLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = MyLayoutBinding.inflate(layoutInflater)
        setContentView(layout.root)
    }
}