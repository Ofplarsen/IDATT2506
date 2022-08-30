package edu.ntnu.oflarsen.idatt2506.oving4

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.ListFragment

class ListFragment  : ListFragment() {

    private var mListener: OnFragmentInteractionListener? = null

    private var movies: Array<String> = arrayOf()
    private var pictures : TypedArray? = null
    private var descriptions: Array<String> = arrayOf()
    private var clicked = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movies = resources.getStringArray(R.array.movies)
        descriptions = resources.getStringArray(R.array.movie_descs)
        pictures = resources.obtainTypedArray(R.array.pictures)

        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, movies)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
            )
        }


    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(text: String?, picture: Drawable?, description: String?)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        setMovie(position)
        clicked = position
    }

    private fun setMovie(position: Int){
        mListener!!.onFragmentInteraction(movies[position], pictures!!.getDrawable(position), descriptions[position])
    }
}