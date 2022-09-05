package edu.ntnu.oflarsen.idatt2506.oving5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun login(v : View?){
        val name = findViewById<EditText>(R.id.name)
        val card = findViewById<EditText>(R.id.card)

        if(!checkInput(name.text.toString(), card.text.toString()))
            return

        val user = arrayOf(name.text.toString(), card.text.toString())
        setResult(RESULT_OK, Intent().putExtra("user", user))
        finish()
    }


    private fun checkInput(name: String, card: String): Boolean{
        if(name.isBlank() && card.isBlank()){
            Log.i("login", "Tried logging in with name and card blank")
            Toast.makeText(this, "Please input name and credit card!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.isBlank()){
            Log.i("login", "Tried logging in with name blank")
            Toast.makeText(this, "Please input name!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(card.isBlank()){
            Log.i("login", "Tried logging in with card blank")
            Toast.makeText(this, "Please input credit card!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true;
    }
}