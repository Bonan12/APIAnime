package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("per_page")
    val per_page: Int? = null,
    @SerialName("total")
    val total: Int? = null
)