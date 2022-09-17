package edu.ntnu.oflarsen.idatt2506.oving7.service

import android.content.Context
import edu.ntnu.oflarsen.idatt2506.oving7.managers.DatabaseManager
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie

class Database (context: Context) : DatabaseManager(context) {

    init {
        try {
            this.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun init(movies: ArrayList<Movie>){
        movies.forEach { this.insertMovie(it) }
    }

    val allMovies: ArrayList<String>
        get() = performQuery(TABLE_MOVIE, arrayOf(MOVIE_TITLE))

    val allDirectors : ArrayList<String>
        get() {
            val select = arrayOf("$TABLE_PERSON.$PERSON_FIRSTNAME", "$TABLE_PERSON.$PERSON_LASTNAME")
            val from = arrayOf(TABLE_PERSON, TABLE_MOVIE, TABLE_MOVIE_ACTOR)
            val join = JOIN_MOVIE_PERSON
            val where = "$TABLE_MOVIE.$MOVIE_DIRECTOR NOT IN (SELECT $TABLE_MOVIE_ACTOR.$PERSON_ID FROM $TABLE_MOVIE_ACTOR) DISTINCT"
            return performRawQuery(select ,from ,join,where)
        }

    val allActors: ArrayList<String>
        get() {
            val select = arrayOf("$TABLE_PERSON.$PERSON_FIRSTNAME", "$TABLE_PERSON.$PERSON_LASTNAME")
            val from = arrayOf(TABLE_PERSON, TABLE_MOVIE, TABLE_MOVIE_ACTOR)
            val join = JOIN_MOVIE_ACTOR
            val where = "DISTINCT"
            return performRawQuery(select ,from ,join,where)
        }

    /*
    fun getAuthorsByBook(title: String): ArrayList<String> {
        /*
        val select = arrayOf("$TABLE_AUTHOR.$AUTHOR_NAME")
        val from = arrayOf(TABLE_AUTHOR, TABLE_BOOK, TABLE_AUTHOR_BOOK)
        val join = JOIN_AUTHOR_BOOK
        val where = "$TABLE_BOOK.$BOOK_TITLE='$title'"
        */
        /*
        You can also just write out the querys manually like below, but this increases the chance of
        spelling mistakes and, creates a lot of work if you want to change names of fields etc.
        later.
         */
        val query =
            "SELECT AUTHOR.name FROM AUTHOR, BOOK, AUTHOR_BOOK " + "WHERE AUTHOR._id = AUTHOR_BOOK.author_id " + "and BOOK._id = AUTHOR_BOOK.book_id " + "and BOOK.title = '$title'"

        return performRawQuery(select, from, join, where)
    }

     */
}