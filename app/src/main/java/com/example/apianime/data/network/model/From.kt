package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class From(
    @SerialName("day")
    val day: Int? = null,
    @SerialName("month")
    val month: Int? = null,
    @SerialName("year")
    val year: Int? = null
)