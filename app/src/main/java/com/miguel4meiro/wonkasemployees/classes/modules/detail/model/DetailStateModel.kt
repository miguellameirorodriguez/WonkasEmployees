package com.miguel4meiro.wonkasemployees.classes.modules.detail.model

import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa

data class DetailStateModel(
    val error: String? = null,
    val isLoading: Boolean = false,
    val loompa: Loompa? = null
)
