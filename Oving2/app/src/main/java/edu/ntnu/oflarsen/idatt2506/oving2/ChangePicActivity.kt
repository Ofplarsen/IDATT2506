package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ChangePicActivity : Activity() {
    var flagValue = R.drawable.lukas;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pic)
    }

    fun onClickFinishActivity(v: View?) { finish() }

    fun onClickChangePic(v: View?) {
        val currentImageIsUK: Boolean = flagValue == R.drawable.lukas
        flagValue = if (currentImageIsUK) R.drawable.steffen else R.drawable.lukas
        (findViewById<View>(R.id.imageView) as ImageView).setImageResource(flagValue)
    }
}