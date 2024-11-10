package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Broadcast(
    @SerialName("day")
    val day: String? = null,
    @SerialName("string")
    val string: String? = null,
    @SerialName("time")
    val time: String? = null,
    @SerialName("timezone")
    val timezone: String? = null
)