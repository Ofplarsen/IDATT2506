package edu.ntnu.oflarsen.idatt2506.oving7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import edu.ntnu.oflarsen.idatt2506.oving7.databinding.MyLayoutBinding
import edu.ntnu.oflarsen.idatt2506.oving7.managers.FileManager
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie
import edu.ntnu.oflarsen.idatt2506.oving7.models.Person
import edu.ntnu.oflarsen.idatt2506.oving7.service.Database

class MainActivity : AppCompatActivity() {
    private lateinit var db: Database
    private lateinit var layout: MyLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = MyLayoutBinding.inflate(layoutInflater)
        setContentView(layout.root)

        db = Database(this)
        initMovies()
    }


    private fun showResults(list: java.util.ArrayList<String>, choice: Int) {
        val res = StringBuffer("")
        for (s in list) res.append("$s\n")
        layout.result.text = res
        layout.info.text = getString(choice)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        menu.add(0, 1, 0, getString(R.string.choice_1))
        menu.add(0, 2, 0,  getString(R.string.choice_2))
        menu.add(0, 3, 0,  getString(R.string.choice_3))
        menu.add(0, 4, 0,  getString(R.string.choice_4))
        menu.add(0, 5, 0,  getString(R.string.choice_5))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> startActivity(Intent("oflarsen.idatt2506.SettingsActivity"))
            1             -> showResults(db.allMovies, R.string.choice_1)
            2             -> showResults(db.allActors,  R.string.choice_2)
            3             -> showResults(db.allDirectors,  R.string.choice_3)
            4             -> showResults(db.getMoviesByDirector("Nolan"),  R.string.choice_4)
            5             -> showResults(db.getActorsInMovie("Howls Moving Castle"),  R.string.choice_5)
            else          -> return false
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initMovies(){
        val movies = FileManager(this).readMovies()

        db.init(movies)

        FileManager(this).writeMovies("movies2.txt", movies)
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
        movies.add(Movie(null,"Howls Moving Castle", Person(null, "Hayao", "Miyazaki"),
            arrayListOf(Person(null, "Christian", "Bale"), Person(null, "Lauren", "Bacall"))
        ))
        return movies
    }
}