package edu.ntnu.oflarsen.idatt2506.oving5.coroutines

import android.app.Activity
import android.os.Bundle
import edu.ntnu.oflarsen.idatt2506.oving5.R
import edu.ntnu.oflarsen.idatt2506.oving5.coroutines.Util.Companion.currentTime
import edu.ntnu.oflarsen.idatt2506.oving5.coroutines.Util.Companion.sleep

class TimeActivity : Activity() {
    private var counter = 1

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        while (true) {
            runClock()
            sendSMS()
        }
    }


    /**
     * send a new SMS every 5 seconds.
     */
    private fun sendSMS() {
        println("Sender SMS $counter...")
        sleep(5000)
        counter++
        println("Fullført SMS $counter")
    }



    /**
     * clock that updates every second.
     */
    private fun runClock() {
        println("Nåværende tid: ${currentTime()}")
        sleep(1000)
    }
}