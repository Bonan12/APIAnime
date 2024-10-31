package com.example.apianime.domain.repo

import com.example.apianime.domain.model.Title
import kotlinx.coroutines.flow.Flow


interface ITitleRepository {
    suspend fun getTitles(): Flow<List<Title>>
}