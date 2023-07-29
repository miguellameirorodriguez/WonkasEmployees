package com.miguel4meiro.wonkasemployees.classes.models.app

data class LoompasResponse(
    val current: Int,
    val total: Int,
    val loompasList: List<Loompa>
)
