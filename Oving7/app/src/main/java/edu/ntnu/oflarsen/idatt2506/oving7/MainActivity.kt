package edu.ntnu.oflarsen.idatt2506.oving7

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.ntnu.oflarsen.idatt2506.oving7.databinding.MyLayoutBinding
import edu.ntnu.oflarsen.idatt2506.oving7.managers.FileManager
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie
import edu.ntnu.oflarsen.idatt2506.oving7.models.Person

class MainActivity : AppCompatActivity() {

    private lateinit var layout: MyLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = MyLayoutBinding.inflate(layoutInflater)
        setContentView(layout.root)
        FileManager(this).writeMovies()
    }

    private fun getMovies(): List<Movie>{
        var movies: ArrayList<Movie> = arrayListOf()
        movies.add(Movie(null,"Inception", Person(null, "Christopher", "Nolan"),
            arrayListOf(Person(null, "Leo", "Di Caprio"), Person())
        ))
    }
}