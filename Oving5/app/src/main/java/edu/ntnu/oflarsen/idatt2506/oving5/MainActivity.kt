package edu.ntnu.oflarsen.idatt2506.oving5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
class MainActivity : Activity() {
    private val requestCodeLogin = 0
    private lateinit var user: User
    private val network: HttpWrapper = HttpWrapper(URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLogin()
        setContentView(R.layout.activity_main)
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

    private fun initGame(){
        val map = mapOf<String, String>("navn" to user.name, "kortnummer" to user.card)
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
                setText(response)

            }
        }
    }
}