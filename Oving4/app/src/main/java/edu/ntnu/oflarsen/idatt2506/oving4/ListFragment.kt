package edu.ntnu.oflarsen.idatt2506.oving4

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment

class ListFragment  : ListFragment() {

    private var movies: Array<String> = arrayOf()
    private var descriptions: Array<String> = arrayOf()
    private var clicked = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movies = resources.getStringArray(R.array.movies)
        descriptions = resources.getStringArray(R.array.movie_descs)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, movies)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Toast.makeText(context, movies[position], Toast.LENGTH_SHORT).show()
        clicked = position
    }
}