package com.miguel4meiro.wonkasemployees.classes.models.app

enum class SortBy {
    NONE,
    GENDER,
    PROFESSION,
    BOTH
}

fun giveSort(genderFilter: GenderEnum?, professionFilter: ProfessionEnum?): SortBy {
    return when {
        genderFilter == null && professionFilter == null -> SortBy.NONE
        genderFilter != null && professionFilter == null -> SortBy.GENDER
        genderFilter == null && professionFilter != null -> SortBy.PROFESSION
        else -> SortBy.BOTH
    }
}