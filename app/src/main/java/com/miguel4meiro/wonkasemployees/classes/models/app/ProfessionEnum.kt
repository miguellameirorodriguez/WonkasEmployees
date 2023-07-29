package com.miguel4meiro.wonkasemployees.classes.models.app

enum class ProfessionEnum(val key: String) {
    DEVELOPER("Developer"),
    METALWORKER("Metalworker"),
    GEMCUTTER("Gemcutter"),
    MEDIC("Medic"),
    BREWER("Brewer"),
    NONE("none");

    companion object {
        fun byKey(key: String): ProfessionEnum {
            return values().firstOrNull { it.key == key } ?: NONE
        }
    }
}

fun ProfessionEnum?.getValue(): String? {
    return when(this) {
        ProfessionEnum.DEVELOPER -> this.key
        ProfessionEnum.METALWORKER -> this.key
        ProfessionEnum.GEMCUTTER -> this.key
        ProfessionEnum.MEDIC -> this.key
        ProfessionEnum.BREWER -> this.key
        ProfessionEnum.NONE -> null
        null -> null
    }
}