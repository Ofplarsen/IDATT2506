package edu.ntnu.oflarsen.idatt2506.oving5.coroutines

import android.util.Log
import java.util.*

class Util {companion object {

    /**
     * Get and format time string from Calendar
     */
    fun currentTime(): String {
        val cal = Calendar.getInstance()
        val hour = cal[Calendar.HOUR_OF_DAY]
        val minute = cal[Calendar.MINUTE]
        val second = cal[Calendar.SECOND]
        return "${toTimeString(hour)}:${toTimeString(minute)}:${toTimeString(second)}"
    }

    /**
     * Pause execution (to mimic a slow request or time-consuming task)
     */
    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            Log.e("sleep(millis)", "Interrupted sleep")
        }
    }


    private fun toTimeString(value: Int): String {
        val string = value.toString()
        return if (string.length == 1) "0$string" else string
    }
}
}