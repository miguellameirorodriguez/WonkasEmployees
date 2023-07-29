package com.miguel4meiro.wonkasemployees.classes.repositories.loompas

import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.LoompasResponse
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum

interface LoompaRepositoryInterface {

    suspend fun getLoompas(page: Int, genderFilter: GenderEnum?, professionFilter: ProfessionEnum?): LoompasResponse
    suspend fun getLoompa(id: Int): Loompa
}