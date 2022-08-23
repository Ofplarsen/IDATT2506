package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

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
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show()
        finish()
    }

    fun onClickChangePic(v: View?) {
        val currentImageIsUK: Boolean = flagValue == R.drawable.lukas
        flagValue = if (currentImageIsUK) R.drawable.steffen else R.drawable.lukas
        (findViewById<View>(R.id.imageView) as ImageView).setImageResource(flagValue)
        alert()
    }

    fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Description")
        builder.setPositiveButton("Confirm") { dialog: DialogInterface, which:
            Int ->
                Toast.makeText(applicationContext, "Confirm", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { _,_ ->
            Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("Neutral") { _,_ ->
            Toast.makeText(applicationContext, "Neutral", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}