package com.miguel4meiro.wonkasemployees.classes.models.app

data class Loompa(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val favorites: Favorite,
    val gender: GenderEnum,
    val image: String,
    val profession: ProfessionEnum,
    val email: String,
    val age: Int,
    val country: String,
    val height: Int,
) {

    val fullName = "$firstName $lastName"
}
