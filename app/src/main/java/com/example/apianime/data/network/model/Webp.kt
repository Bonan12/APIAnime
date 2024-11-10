package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webp(
    @SerialName("image_url")
    val image_url: String? = null,
    @SerialName("large_image_url")
    val large_image_url: String? = null,
    @SerialName("small_image_url")
    val small_image_url: String? = null
)