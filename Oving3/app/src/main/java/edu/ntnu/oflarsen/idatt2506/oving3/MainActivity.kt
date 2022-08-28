package edu.ntnu.oflarsen.idatt2506.oving3

import android.app.Activity
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private var movies: Array<String> = arrayOf()
    private var ratings: Array<String> = arrayOf()
    private var pictures: TypedArray? = null
    private var imageIDs: IntArray = intArrayOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies = resources.getStringArray(R.array.movies)
        ratings = resources.getStringArray(R.array.movie_ratings)
        pictures = resources.obtainTypedArray(R.array.pictures)

        initQuitButton()
        initSpinner()
        initList()
        //initMovieButtons() Deprecated
    }

    private fun initQuitButton(){
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { finish() }
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, movies)
        val spinner = findViewById<Spinner>(R.id.spinner)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = "Rating: " + ratings[position]
                findViewById<TextView>(R.id.beskrivelse).text = text
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    private fun initList(){
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, valgt: View, posisjon: Int, id: Long ->
                findViewById<TextView>(R.id.beskrivelse).text = ratings[posisjon]
                findViewById < Spinner >(R.id.spinner).setSelection(posisjon)
            }
    }

    private fun initMovieButtons() {
        val layout = findViewById<LinearLayout>(R.id.bildeknapp_layout)
        for (i in 0 until pictures!!.length()){
            val button = ImageButton(this, )
            button.setImageDrawable(pictures!!.getDrawable(i))
            layout.addView(button)
            button.setOnClickListener {
                showBigPicture(i)
            }
        }
    }


    private fun showBigPicture(nr: Int){
        startActivity(Intent("edu.ntnu.oflarsen.idatt2506.ShowMovie").putExtra("nr", nr))
    }
}

