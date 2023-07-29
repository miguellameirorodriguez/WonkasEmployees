package com.miguel4meiro.wonkasemployees.classes.modules.list.model

import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.SortBy
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum

data class ListStateModel(
    val error: String? = null,
    val loading: Boolean = false,
    val loompasList: List<Loompa> = listOf(),
    val sortBy: SortBy = SortBy.NONE,
    val genderFilter: GenderEnum? = null,
    val professionFilter: ProfessionEnum? = null
)
