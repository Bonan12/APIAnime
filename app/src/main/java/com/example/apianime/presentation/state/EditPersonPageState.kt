package com.example.apianime.presentation.state

import android.net.Uri

interface EditPersonPageState {
    val name: String
    val role: String
    val photoUri: Uri
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}