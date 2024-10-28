package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    @SerialName("endings")
    val endings: List<String?>? = null,
    @SerialName("openings")
    val openings: List<String?>? = null
)