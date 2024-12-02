package com.example.apianime.domain.usecase

import com.example.apianime.domain.model.Title
import com.example.apianime.domain.repo.ITitleRepository

interface ITitleUseCase{
    suspend fun getTitlesFromResponse() : Set<Title>
}

class TitleUseCase(
    val repo: ITitleRepository
): ITitleUseCase{
    override suspend fun getTitlesFromResponse(): Set<Title> {
        return repo.getTitlesFromResponse()
    }
}