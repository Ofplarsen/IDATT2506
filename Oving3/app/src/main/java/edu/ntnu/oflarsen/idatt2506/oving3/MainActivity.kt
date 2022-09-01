package edu.ntnu.oflarsen.idatt2506.oving3

import android.app.Activity
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : Activity() {
    private var friends: ArrayList<Friend> = arrayListOf(Friend("Olav", "01.01.2022"))
    private val requestCodeAdd: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_friends)
        initList()
    }

    private fun initList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, valgt: View, posisjon: Int, id: Long ->
                //findViewById<TextView>(R.id.beskrivelse).text = dyrebeskrivelse[posisjon]
                //findViewById<Spinner>(R.id.spinner).setSelection(posisjon)
            }
    }

    fun addFriendStart(v: View?) {
        val intent = Intent("oflarsen.idatt2506.AddFriendActivity")
        startActivityForResult(intent, requestCodeAdd)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        if(requestCodeAdd == requestCode){
            val name = data.getStringExtra("")
            val date = data.getStringExtra("Date")
            if(date == null || name == null){
                return
            }
            friends.add(Friend(name, date))
            return
        }


    }

}