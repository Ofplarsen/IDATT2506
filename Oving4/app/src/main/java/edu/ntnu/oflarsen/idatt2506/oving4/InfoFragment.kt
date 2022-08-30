package edu.ntnu.oflarsen.idatt2506.oving4

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false);
    }

    fun setMovie(title: String?, picture: Drawable?, description: String?){
        requireView().findViewById<TextView>(R.id.title).text = title
        requireView().findViewById<TextView>(R.id.movie_desc).text = description
        requireView().findViewById<ImageView>(R.id.movie_poster).setImageDrawable(picture)
    }
}