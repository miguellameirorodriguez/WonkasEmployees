package com.miguel4meiro.wonkasemployees.classes.models.api

import com.google.gson.annotations.SerializedName
import com.miguel4meiro.wonkasemployees.classes.extensions.valueOrEmpty
import com.miguel4meiro.wonkasemployees.classes.extensions.valueOrZero
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum

data class LoompaDto(
    val id: Int? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("favorite")
    val favorites: FavoriteDto? = null,
    val gender: String? = null,
    val image: String? = null,
    val profession: String? = null,
    val email: String? = null,
    val age: Int? = null,
    val country: String? = null,
    val height: Int? = null
) {

    fun toModel(): Loompa {
        return Loompa(
            id.valueOrZero(),
            firstName.valueOrEmpty(),
            lastName.valueOrEmpty(),
            favorites.valueOrEmpty(),
            GenderEnum.byKey(gender.valueOrEmpty()),
            image.valueOrEmpty(),
            ProfessionEnum.byKey(profession.valueOrEmpty()),
            email.valueOrEmpty(),
            age.valueOrZero(),
            country.valueOrEmpty(),
            height.valueOrZero()
        )
    }
}

fun LoompaDto?.valueOrEmpty() = (this ?: LoompaDto()).toModel()
