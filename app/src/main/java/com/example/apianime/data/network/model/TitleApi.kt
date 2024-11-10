package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleApi(
    @SerialName("data")
    val data: Data? = Data()
)