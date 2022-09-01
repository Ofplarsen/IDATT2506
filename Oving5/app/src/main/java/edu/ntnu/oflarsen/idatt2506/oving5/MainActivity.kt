package edu.ntnu.oflarsen.idatt2506.oving5

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.ntnu.oflarsen.idatt2506.oving5.http.HTTP
import edu.ntnu.oflarsen.idatt2506.oving5.http.HttpWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray

const val URL = "https://bigdata.idi.ntnu.no/mobil/ekko.jsp"
const val URL_JSON = "https://simplifiedcoding.net/demos/marvel/"

class MainActivity : AppCompatActivity() {

    private val network: HttpWrapper = HttpWrapper(URL)
    //private val network: HttpWrapper = HttpWrapper(URL_JSON)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add("GET").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        menu.add("POST").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        menu.add("GET (header)").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "GET" -> performRequest(HTTP.GET, requestParameters())
            "POST" -> performRequest(HTTP.POST, requestParameters())
            "GET (header)" -> performRequest(HTTP.GET_WITH_HEADER, requestParameters())
            else -> return false
        }
        return true
    }

    /**
     * Create a map with parameters for HTTP requests
     */
    private fun requestParameters(): Map<String, String> {
        val firstName = "Øystein Marius"
        val lastName = "Knaus"
        return mapOf(
            "fornavn" to firstName,
            "etternavn" to lastName,
        )
    }

    /**
     * Utfør en HTTP-forespørsel separat fra hovedtråden
     */
    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> network.post(parameterList)
                    HTTP.GET_WITH_HEADER -> network.getWithHeader(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }

            // Endre UI på hovedtråden
            MainScope().launch {
                setResult(response)
                //setResult(formatJsonString(response))
            }
        }
    }

    /**
     * Show result from server in UI
     */
    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.result).text = response
    }

    /**
     * Mange nettsider bruker JSON objekter når de håndterer data, for å konvertere til JSON kan
     * du bruke
     * ```kotlin
     * val json = JSONObject(jsonFormattedString)
     * ```
     * Det samme gjelder dersom du mottar en tabell med Json objekter
     * ```kotlin
     * val json = JSONObject(jsonFormattedString)
     * ```
     * Metoden under konvertere responsesn fra tjeneren til en JSONArray og deretter tilbake til
     * en formattert streng til å vise i UI, denne metoden brukes ved *GET* forespørsler til
     * **URL_JSON**.
     *
     * @param str må være en en JSONArray-formattert streng f.eks.
     * ```
     * [{"a": 1}, {"b", 2}]
     * ```
     */
    private fun formatJsonString(str: String): String {
        return try {
            JSONArray(str).toString(4)
        } catch (e: Exception) {
            Log.e("formatJsonString()", e.toString())
            e.message!!
        }
    }
}
