package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jpg(
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("large_image_url")
    val largeImageUrl: String? = null,
    @SerialName("small_image_url")
    val smallImageUrl: String? = null
)