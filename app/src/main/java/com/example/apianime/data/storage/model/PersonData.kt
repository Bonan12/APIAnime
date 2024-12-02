package com.example.apianime.data.storage.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonData(
    val name: String? = null,
    val role: String? = null,
    val photo: String? = null
)