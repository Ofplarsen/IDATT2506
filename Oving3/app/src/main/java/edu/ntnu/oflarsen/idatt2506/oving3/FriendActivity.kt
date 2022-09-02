package edu.ntnu.oflarsen.idatt2506.oving3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*

class FriendActivity : Activity() {
    private var mode = emptyArray<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_friend)
        if(intent.getStringArrayExtra("mode") != null){
            mode = intent.getStringArrayExtra("mode") as Array<String>
            initEdit()
        }

    }

    private fun initEdit(){
        val button = findViewById<Button>(R.id.btn_friend)
        button.text = getString(R.string.edit_friend)
        button.setOnClickListener {
            val name = findViewById<EditText>(R.id.name)
            val date = findViewById<EditText>(R.id.date)

            if (!checkInput(name.text.toString(), date.text.toString()))
                return@setOnClickListener

            val friend = arrayOf(name.text.toString(), date.text.toString(), mode[0])
            setResult(RESULT_OK, Intent().putExtra("friend", friend))
            finish()
        }
        val name = findViewById<EditText>(R.id.name)
        val date = findViewById<EditText>(R.id.date)
        name.setText(mode[0])
        date.setText(mode[1])
    }



    fun addFriend(v : View?){
        val name = findViewById<EditText>(R.id.name)
        val date = findViewById<EditText>(R.id.date)

        if(!checkInput(name.text.toString(), date.text.toString()))
            return

        val friend = arrayOf(name.text.toString(), date.text.toString())
        setResult(RESULT_OK, Intent().putExtra("friend", friend))
        finish()
    }


    private fun checkInput(name: String, date: String): Boolean{
        if(name.isBlank() && name.isBlank()){
            Toast.makeText(this, "Please input name and brith date!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.isBlank()){
            Toast.makeText(this, "Please input name!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.isBlank()){
            Toast.makeText(this, "Please input brith date!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true;
    }

}