package com.miguel4meiro.wonkasemployees.classes.modules.list.viewmodel

import androidx.lifecycle.ViewModel
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum
import com.miguel4meiro.wonkasemployees.classes.modules.list.model.ListStateModel
import kotlinx.coroutines.flow.StateFlow

abstract class ListViewModelInterface: ViewModel() {
    abstract val uiState: StateFlow<ListStateModel>

    abstract fun getLoompas()
    abstract fun loadNextPage()
    abstract fun applyFilter(genderFilter: GenderEnum?, professionFilter: ProfessionEnum?)
    abstract fun resetFilters()
}