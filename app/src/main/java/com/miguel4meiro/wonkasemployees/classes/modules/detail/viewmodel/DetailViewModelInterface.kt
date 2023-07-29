package com.miguel4meiro.wonkasemployees.classes.modules.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.miguel4meiro.wonkasemployees.classes.modules.detail.model.DetailStateModel
import kotlinx.coroutines.flow.StateFlow

abstract class DetailViewModelInterface: ViewModel() {
    abstract val uiState: StateFlow<DetailStateModel>

    abstract fun getLoompa(id: Int)
}