package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class CalculatorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    /**
     *  Avslutt aktivitet og gå tilbake til MainActivity, legg ved intent med riktig flagg
     */
    fun onClickFinishActivity(v: View?) {

        setResult(RESULT_OK, Intent().putExtra("numberValue", 12))
        finish()
    }
}