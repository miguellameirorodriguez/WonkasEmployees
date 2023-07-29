package com.miguel4meiro.wonkasemployees.classes.services.api.loompas

import com.miguel4meiro.wonkasemployees.classes.models.api.LoompaDto
import com.miguel4meiro.wonkasemployees.classes.models.api.LoompasResponseDto

interface LoompaServiceInterface {

    suspend fun getLoompas(page: Int): LoompasResponseDto
    suspend fun getLoompa(id: Int): LoompaDto
}