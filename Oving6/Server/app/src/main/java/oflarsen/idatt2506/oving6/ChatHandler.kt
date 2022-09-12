package oflarsen.idatt2506.oving6

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatHandler {

    companion object {

        /**
         * function that performs the callback function every second with an increasing number
         * counter
         *
         * @param callback Function that takes an Int as parameter and returns nothing.
         * (Unit) means the same as "void" in Java.
         */
        fun getNumbersEverySecond(callback: (number: Int) -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                var numberCounter = 0
                while (true) {
                    delay(1000) // a time consuming task, or something we are waiting for
                    callback(numberCounter) // Perform callback function
                    numberCounter++
                }
            }
        }

        /**
         * Exactly the same as above, only now the callback has a return value
         */
        fun getChat(callback: (number: Int) -> String) {
            CoroutineScope(Dispatchers.IO).launch {
                var numberCounter = 0
                while (true) {
                    delay(1000)
                    val resultOfCallback: String = callback(numberCounter)
                    println(resultOfCallback)
                    numberCounter++
                }
            }
        }
    }
}