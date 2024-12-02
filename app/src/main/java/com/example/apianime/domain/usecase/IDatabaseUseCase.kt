package com.example.apianime.domain.usecase

import com.example.apianime.data.network.repo.TitleRepository
import com.example.apianime.domain.model.Title

/**
 * @author Evgenii
 */

interface IDatabaseUseCase{
    suspend fun save(title: Title)

    suspend fun getTitlesFromDatabase() : Set <Title>
}

class DatabaseUseCase(private val repository: TitleRepository) : IDatabaseUseCase{
    override suspend fun save(title: Title) {
        repository.save(title)
    }

    override suspend fun getTitlesFromDatabase(): Set<Title> {
        return repository.getTitlesFromDatabase()
    }

}