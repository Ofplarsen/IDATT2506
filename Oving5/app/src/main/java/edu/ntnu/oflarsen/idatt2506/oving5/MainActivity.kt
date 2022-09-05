package edu.ntnu.oflarsen.idatt2506.oving5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
class MainActivity : Activity() {
    private val requestCodeLogin = 0
    private var user: User? = null
    private val network: HttpWrapper = HttpWrapper(URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLogin()
    }

    private fun startLogin(){
        val intent = Intent("oflarsen.idatt2506.login")
        startActivityForResult(intent, requestCodeLogin)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        if(requestCodeLogin == requestCode){
            val userInfo = data.getStringArrayExtra("user")
            user = User(name = userInfo!![0], card = userInfo[1])
            Log.i("login", "User: (${user.toString()}) logged in")
            initGame()
            return
        }

    }

    fun tryNumber(v: View){
        val number = findViewById<EditText>(R.id.number).text
        val map = mapOf<String, String>("tall" to number.toString())
        performRequest(HTTP.GET, map)
    }

    private fun initGame(){
        setContentView(R.layout.activity_main)
        val map = mapOf<String, String>("navn" to user!!.name, "kortnummer" to user!!.card)
        performRequest(HTTP.GET, map)
    }

    private fun setText(response: String?) {
        Log.i("http-request", "Response: $response")
        findViewById<TextView>(R.id.result).text = response
    }

    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> {

                        network.get(parameterList)
                    }
                    HTTP.POST -> network.post(parameterList)
                    HTTP.GET_WITH_HEADER -> network.getWithHeader(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }


            MainScope().launch {
                if(response.contains("som kommer inn på ditt kort") || response.contains("Beklager")){
                    findViewById<Button>(R.id.btn_number).text = getString(R.string.restart)
                    findViewById<EditText>(R.id.number).isEnabled = false
                    findViewById<Button>(R.id.btn_number).setOnClickListener {
                        resetGame()
                    }
                }
                setText(response)

            }
        }
    }

    private fun resetGame(){
        user = null
        startLogin()

    }
}