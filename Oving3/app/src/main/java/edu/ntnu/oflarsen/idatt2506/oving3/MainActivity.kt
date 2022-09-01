package edu.ntnu.oflarsen.idatt2506.oving3

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var friends: Array<Friend> = arrayOf(Friend("Olav", "01.01.2022"))


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

}