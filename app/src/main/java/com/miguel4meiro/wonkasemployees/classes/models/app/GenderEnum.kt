package com.miguel4meiro.wonkasemployees.classes.models.app

enum class GenderEnum(val key: String) {
    MALE("M"),
    FEMALE("F"),
    NONE("none");

    companion object {
        fun byKey(key: String): GenderEnum {
            return values().firstOrNull { it.key == key } ?: NONE
        }
    }
}

fun GenderEnum?.getValue(): String? {
    return when(this) {
        GenderEnum.MALE -> this.key
        GenderEnum.FEMALE -> this.key
        GenderEnum.NONE -> null
        null -> null
    }
}