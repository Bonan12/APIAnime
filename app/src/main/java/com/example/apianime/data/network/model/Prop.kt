package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    @SerialName("from")
    val from: From? = null,
    @SerialName("string")
    val string: String? = null,
    @SerialName("to")
    val to: To? = null
)