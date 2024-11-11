package com.example.apianime.domain.repo

import com.example.apianime.domain.model.Title


interface ITitleRepository {
    suspend fun getTitles() : Set<Title>

    suspend fun getTitlesFromResponse(): Set<Title>

    suspend fun save(title: Title)

    suspend fun getTitlesFromDatabase() : Set<Title>
}