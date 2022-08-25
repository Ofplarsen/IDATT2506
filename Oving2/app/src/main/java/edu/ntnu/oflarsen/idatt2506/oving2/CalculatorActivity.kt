package edu.ntnu.oflarsen.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
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

        setResult(RESULT_OK, Intent().putExtra("numberValue", 2))
        finish()
    }

    fun onClickMultiply(v: View?){
        val n1 = findViewById<View>(R.id.n1) as TextView
        val n2 = findViewById<View>(R.id.n2) as TextView
        val a = findViewById<View>(R.id.answerInput) as EditText

        val answerUser = a.text.toString().toInt()
        val answer = n1.text.toString().toInt() * n2.text.toString().toInt()

        showToast(answer == answerUser, answer)
    }

    fun onClickAdd(v: View?){
        val n1 = findViewById<View>(R.id.n1) as TextView
        val n2 = findViewById<View>(R.id.n2) as TextView
        val a = findViewById<View>(R.id.answerInput) as EditText

        val answerUser = a.text.toString().toInt()
        val answer = n1.text.toString().toInt() + n2.text.toString().toInt()

        showToast(answer == answerUser, answer)
    }

    private fun showToast(correct: Boolean, answer: Int){
        if(correct){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.wrong) + answer.toString(), Toast.LENGTH_LONG).show()
        }
    }
}