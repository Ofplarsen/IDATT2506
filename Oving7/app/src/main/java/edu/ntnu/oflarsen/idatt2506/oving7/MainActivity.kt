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
        FileManager(this).writeMovies(getMovies())
    }

    private fun getMovies(): List<Movie>{
        var movies: ArrayList<Movie> = arrayListOf()
        movies.add(Movie(null,"Inception", Person(null, "Christopher", "Nolan"),
            arrayListOf(Person(null, "Leo", "Di Caprio"), Person(null, "Tom", "Hardy"))
        ))
        movies.add(Movie(null,"Pulp Fiction", Person(null, "Quentin", "Tarantino"),
            arrayListOf(Person(null, "Samuel L.", "Jackson"), Person(null, "John", "Travolta"))
        ))
        movies.add(Movie(null,"Fight Club", Person(null, "David", "Fincher"),
            arrayListOf(Person(null, "Brad", "Pitt"), Person(null, "Edward", "Norton"))
        ))
        movies.add(Movie(null,"Interstellar", Person(null, "Christopher", "Nolan"),
            arrayListOf(Person(null, "Matthew", "McConaughey"), Person(null, "Anne", "Hathaway"))
        ))
        movies.add(Movie(null,"Howl's Moving Castle", Person(null, "Hayao", "Miyazaki"),
            arrayListOf(Person(null, "Christian", "Bale"), Person(null, "Lauren", "Bacall"))
        ))
        return movies
    }
}