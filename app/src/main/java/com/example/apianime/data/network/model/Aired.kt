package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String? = null,
    @SerialName("prop")
    val prop: Prop? = null,
    @SerialName("to")
    val to: String? = null
)