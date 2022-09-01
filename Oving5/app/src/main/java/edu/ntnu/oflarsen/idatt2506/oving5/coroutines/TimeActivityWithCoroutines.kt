package edu.ntnu.oflarsen.idatt2506.oving5.coroutines

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import edu.ntnu.oflarsen.idatt2506.oving5.R
import edu.ntnu.oflarsen.idatt2506.oving5.coroutines.Util.Companion.currentTime
import edu.ntnu.oflarsen.idatt2506.oving5.coroutines.Util.Companion.sleep
import kotlinx.coroutines.*

class TimeActivityWithCoroutines : Activity() {

	public override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStart() {
		super.onStart()
		runClock()
		startSendingSMS()
	}

	/**
	 * Continually send new SMSs
	 */
	private fun startSendingSMS() {
		var counter = 1
		CoroutineScope(Dispatchers.Default).launch {
			while (true) {
				sendSMS(counter)
				counter++
			}
		}
	}

	private fun sendSMS(smsNr: Int) {
		setSmsText("Sender SMS $smsNr...")
		sleep(5000)
		setSmsText("Fullført SMS $smsNr")
	}



	/**
	 * clock that updates every second.
	 */
	private fun runClock() {
		CoroutineScope(Dispatchers.IO).launch {
			while (true) {
				setClockText("Nåværende tid: ${currentTime()}")
				delay(1000)
			}
		}
	}

	private fun setSmsText(str: String) {
		println(str)
		MainScope().launch {
			//findViewById<TextView>(R.id.sms)?.text = str
		}
	}

	private fun setClockText(str: String) {
		println(str)
		MainScope().launch {
			//findViewById<TextView>(R.id.clock)?.text = str
		}
	}
}
