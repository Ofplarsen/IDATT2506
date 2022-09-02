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
    private var friends: ArrayList<Friend> = arrayListOf(Friend("Olav Finne Præsteng Larsen", "29.09.2001"),
        Friend("Amund F. P. Larsen", "14.04.2004"), Friend("Øyvind F. P. Larsen", "23.03.2008"))
    private val requestCodeAdd: Int = 0
    private val requestCodeEdit: Int = 1
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
                editFriendStart(friends[posisjon])
            }
    }

    fun editFriendStart(friend: Friend){
        val intent = Intent("oflarsen.idatt2506.FriendActivity")
        intent.putExtra("mode", arrayOf(friend.name, friend.date))
        startActivityForResult(intent, requestCodeEdit)
    }

    fun addFriendStart(v: View?) {
        val intent = Intent("oflarsen.idatt2506.FriendActivity")
        startActivityForResult(intent, requestCodeAdd)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        if(requestCodeAdd == requestCode){
            val friend = data.getStringArrayExtra("friend")
            friends.add(Friend(friend?.get(0) ?: "Unknown", friend?.get(1) ?: "Unknown"))
            initList()
            return
        }

        if(requestCodeEdit == requestCode){
            val friend = data.getStringArrayExtra("friend")
            val f = friends.find { it.name == friend!![2] }
            f!!.name = friend?.get(0) ?: "Unknown"
            f.date = friend?.get(1) ?: "01.01.2000"
            initList()
            return
        }

    }

}