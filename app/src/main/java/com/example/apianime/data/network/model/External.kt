package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class External(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)