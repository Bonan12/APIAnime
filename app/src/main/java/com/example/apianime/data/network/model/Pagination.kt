package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("has_next_page")
    val has_next_page: Boolean? = null,
    @SerialName("items")
    val items: Items? = null,
    @SerialName("last_visible_page")
    val last_visible_page: Int? = null
)