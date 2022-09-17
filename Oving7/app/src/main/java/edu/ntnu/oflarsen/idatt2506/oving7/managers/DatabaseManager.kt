package edu.ntnu.oflarsen.idatt2506.oving7.managers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie
import edu.ntnu.oflarsen.idatt2506.oving7.models.Person

open class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_NAME = "MovieDatabase"
        const val DATABASE_VERSION = 1

        const val ID = "_id"

        const val TABLE_MOVIE = "MOVIE"
        const val MOVIE_TITLE = "title"
        const val MOVIE_DIRECTOR = "director"

        const val TABLE_PERSON = "PERSON"
        const val PERSON_FIRSTNAME = "firstname"
        const val PERSON_LASTNAME = "lastname"

        const val TABLE_MOVIE_ACTOR = "MOVIE_ACTOR"
        const val MOVIE_ID = "movie_id"
        const val PERSON_ID = "person_id"


        val JOIN_MOVIE_ACTOR = arrayOf(
            "$TABLE_MOVIE.$ID=$TABLE_MOVIE_ACTOR.$MOVIE_ID",
            "$TABLE_PERSON.$ID=$TABLE_MOVIE_ACTOR.$PERSON_ID"
        )

        val JOIN_MOVIE_PERSON = arrayOf(
            "$TABLE_MOVIE.$ID=$TABLE_PERSON.$ID"
        )


    }

    /**
     *  Create the tables
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """create table $TABLE_MOVIE (
						$ID integer primary key autoincrement, 
                        $MOVIE_TITLE text unique not null,
						$MOVIE_DIRECTOR numeric,
                        FOREIGN KEY($MOVIE_DIRECTOR) REFERENCES $TABLE_PERSON($ID)
						);"""
        )
        db.execSQL(
            """create table $TABLE_PERSON (
						$ID integer primary key autoincrement, 
						$PERSON_FIRSTNAME text unique not null,
						$PERSON_LASTNAME text unique not null
						);"""
        )
        db.execSQL(
            """create table $TABLE_MOVIE_ACTOR (
						$ID integer primary key autoincrement, 
						$MOVIE_ID numeric, 
						$PERSON_ID numeric,
						FOREIGN KEY($MOVIE_ID) REFERENCES $TABLE_MOVIE($ID), 
						FOREIGN KEY($PERSON_ID) REFERENCES $TABLE_PERSON($ID)
						);"""
        )
    }

    /**
     * Drop and recreate all the tables
     */
    override fun onUpgrade(db: SQLiteDatabase, arg1: Int, arg2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIE_ACTOR")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PERSON")
        onCreate(db)
    }

    /**
     *  Drop all information is the database
     */
    fun clear() {
        writableDatabase.use { onUpgrade(it, 0, 0) }
    }


    fun insertMovie(movie: Movie){
        writableDatabase.use { database ->
            val directorId = insertPerson(movie.director)
            movie.director.id = directorId

            val actorIds = arrayListOf<Long>()
            movie.actors.forEach { actorIds.add(insertPerson(it)) }

            val movieId = insertMovie(database,movie)

            linkMovieAndActors(database, actorIds, movieId)
        }
    }

    private fun insertMovie(database: SQLiteDatabase, movie : Movie): Long {

        val values = ContentValues()
        values.put(MOVIE_TITLE, movie.title)
        values.put(MOVIE_DIRECTOR, movie.director.id)


        query(database, TABLE_MOVIE, arrayOf(ID, MOVIE_TITLE), "$MOVIE_TITLE='${movie.title}'").use { cursor ->
            // insert if value doesn't exist
            return if (cursor.count != 0) {
                cursor.moveToFirst()
                cursor.getLong(0) //id of found value
            } else {
                database.insert(TABLE_MOVIE, null, values)
            }
        }
    }

    private fun linkMovieAndActors(database: SQLiteDatabase, actorId: ArrayList<Long>, movieId: Long){
        actorId.forEach { linkMovieAndActor(database, movieId, it) }
    }

    /**
     *  Insert in the *TABLE_AUTHOR_BOOK*, essentially linking an author and a book
     */
    private fun linkMovieAndActor(database: SQLiteDatabase, movieId: Long, actorId: Long) {

        val values = ContentValues()
        values.put(MOVIE_ID, movieId)
        values.put(PERSON_ID, actorId)
        database.insert(TABLE_MOVIE_ACTOR, null, values)

    }

    private fun insertPerson(person: Person): Long{
        writableDatabase.use { database ->

            val personId = insertPerson(database, person.firstname, person.lastname)

            return personId
        }
    }


    private fun insertPerson(database: SQLiteDatabase, firstName: String, lastName: String): Long {

        val values = ContentValues()
        values.put(PERSON_FIRSTNAME, firstName)
        values.put(PERSON_LASTNAME, lastName)


        query(database, TABLE_PERSON, arrayOf(ID, PERSON_LASTNAME), "$PERSON_LASTNAME='$lastName'").use { cursor ->
            // insert if value doesn't exist
            return if (cursor.count != 0) {
                cursor.moveToFirst()
                cursor.getLong(0) //id of found value
            } else {
                database.insert(TABLE_PERSON, null, values)
            }
        }
    }

    /**
     * Pseudo-code for method
     * ```
     *  if (value exists){
     *      return valueId
     *  } else {
     *      insert and return new Id
     *  }
     * ```
     */
    private fun insertValueIfNotExists(
        database: SQLiteDatabase, table: String, field: String, value: String
    ): Long {
        // Query for the value
        query(database, table, arrayOf(ID, field), "$field='$value'").use { cursor ->
            // insert if value doesn't exist
            return if (cursor.count != 0) {
                cursor.moveToFirst()
                cursor.getLong(0) //id of found value
            } else {
                insertValue(database, table, field, value)
            }
        }
    }



    /**
     * Insert the value in given table and field, then return its ID
     */
    private fun insertValue(
        database: SQLiteDatabase, table: String, field: String, value: String
    ): Long {
        val values = ContentValues()
        values.put(field, value.trim())
        return database.insert(table, null, values)
    }


    /**
     * Perform a simple query
     *
     * Not the query() function has almost all parameters as *null*, you should check up on these.
     * maybe you don't even need the performRawQuery() function?
     */
    fun performQuery(table: String, columns: Array<String>, selection: String? = null):
            ArrayList<String> {
        assert(columns.isNotEmpty())
        readableDatabase.use { database ->
            query(database, table, columns, selection).use { cursor ->
                return readFromCursor(cursor, columns.size)
            }
        }
    }

    /**
     * Run a raw query, the parameters are for easier debugging and reusable code
     */
    fun performRawQuery(
        select: Array<String>, from: Array<String>, join: Array<String>, where: String? = null
    ): ArrayList<String> {

        val query = StringBuilder("SELECT ")
        for ((i, field) in select.withIndex()) {
            query.append(field)
            if (i != select.lastIndex) query.append(", ")
        }

        query.append(" FROM ")
        for ((i, table) in from.withIndex()) {
            query.append(table)
            if (i != from.lastIndex) query.append(", ")
        }

        query.append(" WHERE ")
        for ((i, link) in join.withIndex()) {
            query.append(link)
            if (i != join.lastIndex) query.append(" and ")
        }

        if (where != null) query.append(" and $where")

        readableDatabase.use { db ->
            db.rawQuery("$query;", null).use { cursor ->
                return readFromCursor(cursor, select.size)
            }
        }
    }

    /**
     * Read the contents from the cursor and return it as an arraylist
     */
    private fun readFromCursor(cursor: Cursor, numberOfColumns: Int): ArrayList<String> {
        val result = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val item = StringBuilder("")
            for (i in 0 until numberOfColumns) {
                item.append("${cursor.getString(i)} ")
            }
            result.add("$item")
            cursor.moveToNext()
        }
        return result
    }

    /**
     * Use a query with default arguments
     */
    private fun query(
        database: SQLiteDatabase, table: String, columns: Array<String>, selection: String?
    ): Cursor {
        return database.query(table, columns, selection, null, null, null, null, null)
    }
}