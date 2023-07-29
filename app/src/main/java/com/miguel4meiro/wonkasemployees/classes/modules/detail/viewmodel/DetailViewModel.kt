package com.miguel4meiro.wonkasemployees.classes.modules.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.miguel4meiro.wonkasemployees.classes.modules.detail.model.DetailStateModel
import com.miguel4meiro.wonkasemployees.classes.repositories.loompas.LoompaRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: LoompaRepositoryInterface
): DetailViewModelInterface() {
    private val _uiState = MutableStateFlow(DetailStateModel())
    override val uiState: StateFlow<DetailStateModel> get() = _uiState

    override fun getLoompa(id: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val loompa = repository.getLoompa(id)
                _uiState.value = _uiState.value.copy(isLoading = false, loompa = loompa)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.localizedMessage)
            }
        }
    }
}