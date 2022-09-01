package edu.ntnu.oflarsen.idatt2506.oving3

import android.app.Activity
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class AddFriendActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_friend)
    }


    fun addFriend(v : View?){
        val name = findViewById<EditText>(R.id.add_name)
        val date = findViewById<EditText>(R.id.add_date)

        if(!checkInput(name.text.toString(), date.text.toString()))
            return

        setResult(RESULT_OK, Intent().putExtra("Name", name.text.toString()))
        setResult(RESULT_OK, Intent().putExtra("Date", date.text.toString()))
        finish()
    }


    private fun checkInput(name: String, date: String): Boolean{
        if(name.isBlank() && name.isBlank()){
            Toast.makeText(this, "Please input name and date!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.isBlank()){
            Toast.makeText(this, "Please input name!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(name.isBlank()){
            Toast.makeText(this, "Please input date!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true;
    }

}