package com.example.apianime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.IDatabaseUseCase
import com.example.apianime.presentation.mapper.ITitleItemMapper
import com.example.apianime.presentation.model.TitleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author Evgenii
 */
class FavouritesPageViewModel(
    private val mapper: ITitleItemMapper,
    private val usecase: IDatabaseUseCase
) : ViewModel() {
    private val _titles: MutableStateFlow<List<TitleItem>> = MutableStateFlow(emptyList())
    val titles: StateFlow<List<TitleItem>> = _titles

    init {
        refresh()
    }

    fun refresh(){
        viewModelScope.launch {
            load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _titles.value = usecase
                .getTitlesFromDatabase()
                .map { title -> mapper.toUi(title) }
        }
    }
}