package com.example.apianime.presentation.state

import android.net.Uri

interface PersonPageState{
    val name: String
    val role: String
    val link: String
    val photoUri: Uri
}