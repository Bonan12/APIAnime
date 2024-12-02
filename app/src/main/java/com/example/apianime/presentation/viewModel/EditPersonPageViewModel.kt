package com.example.apianime.presentation.viewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.IPersonUseCase
import com.example.apianime.presentation.mapper.IPersonItemMapper
import com.example.apianime.presentation.model.PersonItem
import com.example.apianime.presentation.receiver.CustomBroadcastReceiver
import com.example.apianime.presentation.state.EditPersonPageState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EditPersonPageViewModel(
    private val mapper: IPersonItemMapper,
    private val useCase: IPersonUseCase
) : ViewModel() {
    private var _state = MutableEditPersonPageState()
    val state = _state as EditPersonPageState
    private val format = DateTimeFormatter.ofPattern("HH:mm")

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

    fun updateContext(context: Context){
        _state.context = context
    }

    fun updateTime(input: String) {
        _state.timeString = input
        validateTime()
    }

    fun updateTime(hour: Int, minute: Int) {
        _state.time = LocalTime.of(hour, minute)
        _state.timeString = _state.time.format(format)
        _state.timeError = null
    }

    fun toggleTimer() {
        _state.isNeedToOpenTimer = !state.isNeedToOpenTimer
    }

    fun save(onToBack: () -> Unit) {
        validateTime()
        if (_state.timeError == null) {
            viewModelScope.launch {
                val person = PersonItem(
                    name = state.name,
                    role = state.role,
                    photoUri = state.photoUri
                )
                useCase.save(mapper.toDomain(person))
                setAlarmManager()
                onToBack()
            }
        }
    }

    private fun setAlarmManager() {
        val alarmManager = state.context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(LocalDate.now(), _state.time)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(state.context, CustomBroadcastReceiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putString("NOTIFY", "Твоя роль: ${_state.role}")
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            state.context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            Log.e("alarmManager", "Failed to set reminder")
        }
    }

    private fun validateTime() {
        try {
            _state.time = LocalTime.parse(_state.timeString, format)
            _state.timeError = null
        } catch (e: Exception) {
            _state.timeError = "Неправильный ввод"
        }
    }

    private class MutableEditPersonPageState : EditPersonPageState {
        override var name: String by mutableStateOf("")
        override var role: String by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var time: LocalTime by mutableStateOf(LocalTime.now())
        override var timeString: String by mutableStateOf("")
        override var timeError: String? by mutableStateOf("")
        override var isNeedToShowPermission: Boolean by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
        override var isNeedToOpenTimer: Boolean by mutableStateOf(false)
        override var context: Context? by mutableStateOf(null)
    }
}
