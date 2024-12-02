package com.example.apianime.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.IPersonUseCase
import com.example.apianime.presentation.mapper.IPersonItemMapper
import com.example.apianime.presentation.model.PersonItem
import com.example.apianime.presentation.state.EditPersonPageState
import kotlinx.coroutines.launch

class EditPersonPageViewModel(
    private val mapper: IPersonItemMapper,
    private val useCase: IPersonUseCase
) : ViewModel() {
    private var _state = MutableEditPersonPageState()
    val state = _state as EditPersonPageState

    fun updateName(value: String) {
        _state.name = value
    }

    fun updateRole(value: String) {
        _state.role = value
    }

    fun updatePhoto(value: Uri) {
        _state.photoUri = value
    }

    fun onClosePermission(){
        _state.isNeedToShowPermission = false
    }

    fun onSelectPhoto(){
        _state.isNeedToShowSelect = true
    }

    fun onSelectDismiss(){
        _state.isNeedToShowSelect = false
    }

    fun save(onToBack : () -> Unit) {
        viewModelScope.launch {
            val person = PersonItem(
                name = state.name,
                role = state.role,
                photoUri = state.photoUri
            )
            useCase.save(mapper.toDomain(person))
        }
        onToBack()
    }

    private class MutableEditPersonPageState : EditPersonPageState {
        override var name: String by mutableStateOf("")
        override var role: String by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var isNeedToShowPermission: Boolean by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}
