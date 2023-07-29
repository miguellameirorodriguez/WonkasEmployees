package com.miguel4meiro.wonkasemployees.classes.models.api

import com.google.gson.annotations.SerializedName
import com.miguel4meiro.wonkasemployees.classes.extensions.valueOrEmpty
import com.miguel4meiro.wonkasemployees.classes.models.app.Favorite

data class FavoriteDto(
    val color: String? = null,
    val food: String? = null,
    @SerializedName("random_string")
    val randomString: String? = null,
    val song: String? = null
) {

    fun toModel(): Favorite {
        return Favorite(
            color.valueOrEmpty(),
            food.valueOrEmpty(),
            randomString.valueOrEmpty(),
            song.valueOrEmpty()
        )
    }
}

fun FavoriteDto?.valueOrEmpty() = (this ?: FavoriteDto()).toModel()