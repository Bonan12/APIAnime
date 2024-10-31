package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Relation(
    @SerialName("entry")
    val entry: List<Entry?>? = null,
    @SerialName("relation")
    val relation: String? = null
)