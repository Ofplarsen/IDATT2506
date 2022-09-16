package edu.ntnu.oflarsen.idatt2506.oving7.service

import android.content.Context
import edu.ntnu.oflarsen.idatt2506.oving7.managers.DatabaseManager
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie

class Database (context: Context) : DatabaseManager(context) {

    init {
        try {
            this.clear()
            this.insert("Jane Austen", "Pride and Prejudice")
            this.insert("Harper Lee", "To Kill a Mockingbird")
            this.insert("Charles Dickens", "A Tale of Two Cities")
            this.insert("F. Scott Fitzgerald", "The Great Gatsby")
            this.insert("Carl Bernstein", "All The Presidents Men")
            this.insert("Gabriel García Márquez", "One Hundred Years of Solitude")
            this.insert("Charles Dickens", "Great Expectations")
            this.insert("Bob Woodward", "All The Presidents Men")
            this.insert("Charles Dickens", "Oliver Twist")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    val allMovies: ArrayList<String>
        get() = performQuery(TABLE_MOVIE, arrayOf(MOVIE_TITLE))


    val allBooksAndAuthors: ArrayList<String>
        get() {
            val select = arrayOf("$TABLE_BOOK.$BOOK_TITLE", "$TABLE_AUTHOR.$AUTHOR_NAME")
            val from = arrayOf(TABLE_AUTHOR, TABLE_BOOK, TABLE_AUTHOR_BOOK)
            val join = JOIN_AUTHOR_BOOK

            return performRawQuery(select, from, join)
        }

    fun getBooksByAuthor(author: String): ArrayList<String> {
        val select = arrayOf("$TABLE_BOOK.$BOOK_TITLE")
        val from = arrayOf(TABLE_AUTHOR, TABLE_BOOK, TABLE_AUTHOR_BOOK)
        val join = JOIN_AUTHOR_BOOK
        val where = "$TABLE_AUTHOR.$AUTHOR_NAME='$author'"

        return performRawQuery(select, from, join, where)
    }

    fun getAuthorsByBook(title: String): ArrayList<String> {
        val select = arrayOf("$TABLE_AUTHOR.$AUTHOR_NAME")
        val from = arrayOf(TABLE_AUTHOR, TABLE_BOOK, TABLE_AUTHOR_BOOK)
        val join = JOIN_AUTHOR_BOOK
        val where = "$TABLE_BOOK.$BOOK_TITLE='$title'"

        /*
        You can also just write out the querys manually like below, but this increases the chance of
        spelling mistakes and, creates a lot of work if you want to change names of fields etc.
        later.
         */
        val query =
            "SELECT AUTHOR.name FROM AUTHOR, BOOK, AUTHOR_BOOK " + "WHERE AUTHOR._id = AUTHOR_BOOK.author_id " + "and BOOK._id = AUTHOR_BOOK.book_id " + "and BOOK.title = '$title'"

        return performRawQuery(select, from, join, where)
    }
}