package com.example.apianime.domain.usecase

import com.example.apianime.domain.model.Title
import com.example.apianime.domain.repo.ITitleRepository
import kotlinx.coroutines.flow.Flow

interface ITitleUseCase{
    suspend fun getTitles() : Flow<List<Title>>
}

class TitleUseCase(
    val repo: ITitleRepository
): ITitleUseCase{
    override suspend fun getTitles(): Flow<List<Title>> {
        return repo.getTitles()
    }
}