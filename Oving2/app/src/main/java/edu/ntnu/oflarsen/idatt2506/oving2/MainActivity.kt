package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val numbRequestCode: Int = 0
    private val upperLimit = 100;
    private var value = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartApp(v: View?) {
        val intent = Intent("oflarsen.idatt2506.GenerateNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, numbRequestCode)
    }

    fun onClickStartCalc(v: View?) {
        val intent = Intent("oflarsen.idatt2506.Calculator")
        startActivityForResult(intent, numbRequestCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        if(requestCode == numbRequestCode){
            val value = data.getIntExtra("numberValue", value)
            val numberValue = findViewById<View>(R.id.numbValue) as TextView
            numberValue.text = value.toString()
        }
    }
}