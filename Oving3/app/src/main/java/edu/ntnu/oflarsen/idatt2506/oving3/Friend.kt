package edu.ntnu.oflarsen.idatt2506.oving3

import java.util.*

data class Friend(var name: String, var date: String) {
    override fun toString(): String {
        return "$name\n$date"
    }
}