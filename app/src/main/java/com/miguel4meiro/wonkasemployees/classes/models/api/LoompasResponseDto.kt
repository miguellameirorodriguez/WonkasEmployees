package com.miguel4meiro.wonkasemployees.classes.models.api

import com.google.gson.annotations.SerializedName
import com.miguel4meiro.wonkasemployees.classes.extensions.valueOrZero
import com.miguel4meiro.wonkasemployees.classes.models.app.LoompasResponse

data class LoompasResponseDto(
    val current: Int? = null,
    val total: Int? = null,
    @SerializedName("results")
    val loompasDto: List<LoompaDto>? = null
) {

    fun toModel(): LoompasResponse {
        return LoompasResponse(
            current.valueOrZero(),
            total.valueOrZero(),
            loompasDto?.map { loompaDto -> loompaDto.toModel() } ?: listOf()
        )
    }
}
