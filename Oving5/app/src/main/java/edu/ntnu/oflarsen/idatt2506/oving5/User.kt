package edu.ntnu.oflarsen.idatt2506.oving5

data class User(val name: String, val card: String){
    override fun toString(): String {
        return "User(name='$name', card='$card')"
    }
}

