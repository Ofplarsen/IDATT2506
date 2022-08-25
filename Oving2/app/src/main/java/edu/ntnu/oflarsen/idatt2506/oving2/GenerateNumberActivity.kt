package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class GenerateNumberActivity : Activity() {
    private var upperLimit = 100
    private var randomNumber = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)
        upperLimit = intent.getIntExtra("upperLimit", upperLimit)
    }

    /**
     *  Avslutt aktivitet og gå tilbake til MainActivity, legg ved intent med riktig flagg
     */
    fun onClickFinishActivity(v: View?) {

        setResult(RESULT_OK, Intent().putExtra("numberValue", randomNumber))
        finish()
    }

    fun generateRandomNumber(view: View?){
        randomNumber = (0..upperLimit).random()
        //Toast.makeText(this, randomNumber.toString(), Toast.LENGTH_LONG).show()
        onClickFinishActivity(view)
    }

}