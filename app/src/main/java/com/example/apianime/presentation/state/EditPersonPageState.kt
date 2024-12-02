package com.example.apianime.presentation.state

import android.content.Context
import android.net.Uri
import java.time.LocalTime

interface EditPersonPageState {
    val name: String
    val role: String
    val photoUri: Uri
    val time: LocalTime
    val timeString: String
    val timeError: String?
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
    val isNeedToOpenTimer: Boolean
    val context: Context?
}