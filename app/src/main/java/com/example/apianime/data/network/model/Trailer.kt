package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("embed_url")
    val embedUrl: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("youtube_id")
    val youtubeId: String? = null
)