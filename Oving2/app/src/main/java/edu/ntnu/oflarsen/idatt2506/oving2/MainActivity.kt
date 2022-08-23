package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class MainActivity : Activity() {
    private val flagRequestCode: Int = 1
    private var flagValue = R.drawable.steffen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartChangePicActivity(v: View?) {
        val intent = Intent("oving2.ChangePicActivity")
        intent.putExtra("flag", flagValue)
        startActivityForResult(intent, flagRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (requestCode == flagRequestCode) {
            if (data != null) {
                flagValue = data.getIntExtra("flag", flagValue)
            }
            val flag = findViewById<View>(R.id.imageView) as ImageView
            flag.setImageResource(flagValue)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}