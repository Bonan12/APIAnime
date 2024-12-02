package com.example.apianime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.IDataStoreSavesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * @author Evgenii
 */
class NavigationGraphBarViewModel(
    private val useCase: IDataStoreSavesUseCase
) : ViewModel() {
    private val _isIncreased: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isIncreased: StateFlow<Boolean> = _isIncreased.asStateFlow()

    init {
        observeIsIncreased()
    }

    private fun observeIsIncreased() {
        viewModelScope.launch {
            useCase.get().collect { value ->
               _isIncreased.value = value
            }
        }
    }

    fun updateIsIncreased() {
        viewModelScope.launch {
            _isIncreased.value = useCase.get().first() // Можно оставить для ручного обновления
        }
    }
}