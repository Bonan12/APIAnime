package com.example.apianime.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.ITitleUseCase
import com.example.apianime.presentation.mapper.ITitleItemMapper
import com.example.apianime.presentation.model.TitleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ListPageViewModel(
    usecase: ITitleUseCase,
    mapper: ITitleItemMapper
) : ViewModel() {
    private val _titles: MutableStateFlow<List<TitleItem>> = MutableStateFlow(emptyList())
    val titles: StateFlow<List<TitleItem>> = _titles

    init {
        viewModelScope.launch {
            _titles.value = usecase
                .getTitles()
                .first()
                .map { title -> mapper.invoke(title) }
        }
    }
}