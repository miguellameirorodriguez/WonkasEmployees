package com.miguel4meiro.wonkasemployees.classes.modules.list.viewmodel

import androidx.lifecycle.viewModelScope
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.SortBy
import com.miguel4meiro.wonkasemployees.classes.models.app.giveSort
import com.miguel4meiro.wonkasemployees.classes.modules.list.model.ListStateModel
import com.miguel4meiro.wonkasemployees.classes.repositories.loompas.LoompaRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: LoompaRepositoryInterface
): ListViewModelInterface() {

    private val _uiState = MutableStateFlow(ListStateModel())
    override val uiState: StateFlow<ListStateModel>
        get() = _uiState

    private var lastPage = false
    private var page = 1

    private var chosenGenderFilter: GenderEnum? = null
    private var chosenProfessionFilter: ProfessionEnum? =null

    override fun getLoompas() {
        _uiState.value = _uiState.value.copy(loading = true, loompasList = listOf())
        viewModelScope.launch {
            try {
                val loompasResponse = repository.getLoompas(page, chosenGenderFilter, chosenProfessionFilter)
                lastPage = page >= loompasResponse.total
                page += 1

                _uiState.value = _uiState.value.copy(loading = false, loompasList = loompasResponse.loompasList)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(loading = false, error = e.localizedMessage)
            }
        }
    }

    override fun loadNextPage() {
        if (lastPage || _uiState.value.loading) return
        getLoompas()
    }

    override fun applyFilter(genderFilter: GenderEnum?, professionFilter: ProfessionEnum?) {
        setFilters(genderFilter, professionFilter)

        resetPage()
        _uiState.value = _uiState.value.copy(loompasList = listOf(), sortBy = giveSort(genderFilter, professionFilter), genderFilter = genderFilter, professionFilter = professionFilter)
        getLoompas()
    }

    override fun resetFilters() {
        setFilters(null, null)

        resetPage()
        _uiState.value = _uiState.value.copy(loompasList = listOf(), sortBy = SortBy.NONE, genderFilter = null, professionFilter = null)
        getLoompas()
    }

    private fun setFilters(genderFilter: GenderEnum?, professionFilter: ProfessionEnum?) {
        chosenGenderFilter = genderFilter
        chosenProfessionFilter = professionFilter
    }

    private fun resetPage() {
        page = 1
    }
}