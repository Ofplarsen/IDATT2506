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
    private val numbRequestCode: Int = 0
    private var n1 = true
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
        generateRandom()
    }

    fun onClickAdd(v: View?){
        val n1 = findViewById<View>(R.id.n1) as TextView
        val n2 = findViewById<View>(R.id.n2) as TextView
        val a = findViewById<View>(R.id.answerInput) as EditText

        val answerUser = a.text.toString().toInt()
        val answer = n1.text.toString().toInt() + n2.text.toString().toInt()

        showToast(answer == answerUser, answer)
        generateRandom()
    }

    private fun showToast(correct: Boolean, answer: Int){
        if(correct){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.wrong) + answer.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun generateRandom(){
        val upperLimit = findViewById<View>(R.id.upperLimitInput) as EditText
        val intent = Intent("oflarsen.idatt2506.GenerateNumberActivity")
        intent.putExtra("upperLimit", upperLimit.text.toString().toInt())
        startActivityForResult(intent, numbRequestCode)
        startActivityForResult(intent, numbRequestCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val n = 0
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        if(requestCode == numbRequestCode){
            val value = data.getIntExtra("numberValue", n)
            if(n1){
                val numberValue = findViewById<View>(R.id.n1) as TextView
                numberValue.text = value.toString()
                n1 = !n1
            }else{
                val numberValue = findViewById<View>(R.id.n2) as TextView
                numberValue.text = value.toString()
                n1 = !n1
            }


        }
    }
}