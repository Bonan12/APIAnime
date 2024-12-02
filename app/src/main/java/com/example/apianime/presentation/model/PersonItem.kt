package com.example.apianime.presentation.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
class PersonItem(
    val name: String,
    val role: String,
    @Serializable(with = Serialize::class)
    val photoUri: Uri
) : Parcelable