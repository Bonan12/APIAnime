package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListTitlesResponse(
    @SerialName("data")
    val `data`: List<Data?>? = null,
    @SerialName("pagination")
    val pagination: Pagination? = null
)