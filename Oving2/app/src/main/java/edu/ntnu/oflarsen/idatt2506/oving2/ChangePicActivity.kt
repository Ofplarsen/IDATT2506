package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ChangePicActivity : Activity() {
    private var flagValue = R.drawable.lukas;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pic)

        flagValue = intent.getIntExtra("flag", flagValue)
        val image = findViewById<View>(R.id.imageView) as ImageView
        image.setImageResource(flagValue)
    }

    fun onClickFinishActivity(v: View?) {
        setResult(RESULT_OK, Intent().putExtra("flag", flagValue))
        finish()
    }

    fun onClickChangePic(v: View?) {
        val currentImageIsUK: Boolean = flagValue == R.drawable.lukas
        flagValue = if (currentImageIsUK) R.drawable.steffen else R.drawable.lukas
        (findViewById<View>(R.id.imageView) as ImageView).setImageResource(flagValue)
    }
}