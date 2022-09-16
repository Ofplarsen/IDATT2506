package edu.ntnu.oflarsen.idatt2506.oving7.managers

import androidx.appcompat.app.AppCompatActivity
import edu.ntnu.oflarsen.idatt2506.oving7.models.Movie
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import edu.ntnu.oflarsen.idatt2506.oving7.R
import java.io.*

class FileManager(private val activity: AppCompatActivity) {

    private val filename: String = "movies.txt"
    private val mapper = jacksonObjectMapper()

    private var dir: File = activity.filesDir
    private var file: File = File(dir, filename)

    private var externalDir: File? = activity.getExternalFilesDir(null)
    private var externalFile = File(externalDir, filename)


    private fun write(str: String) {
        PrintWriter(file).use { writer ->
            writer.println(str)
        }
    }

    fun readLine(): String? {
        BufferedReader(FileReader(file)).use { reader ->
            return reader.readLine()
        }
    }

    fun writeMovies(movies: List<Movie>){
        val serialized = mapper.writeValueAsString(movies)
        write(serialized)
    }

    fun readMovies(): List<Movie> {
        val json = readFileFromResFolder(R.raw.movies)

        return mapper.readValue(json)
    }

    /**
     * Open file: *res/raw/id.txt*
     *
     * @param fileId R.raw.filename
     */
    private fun readFileFromResFolder(fileId: Int): String {
        val content = StringBuffer("")
        try {
            val inputStream: InputStream = activity.resources.openRawResource(fileId)
            val reader = BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    content.append(line)
                    content.append("\n")
                    line = reader.readLine()
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}