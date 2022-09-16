package edu.ntnu.oflarsen.idatt2506.oving7.models

data class Movie(val id: Int,val name: String, val director: Person, val actors: List<Person>) {
}