package com.example.apianime.data.storage.mapper

import com.example.apianime.data.storage.model.TitleDb
import com.example.apianime.domain.model.Title

interface ITitleMapper{
    operator fun invoke(title: TitleDb) : Title
}

class TitleMapper() : ITitleMapper {
    override fun invoke(title: TitleDb): Title {
        return Title(
            name = title.name ?: "Нет названия",
            type = title.type ?: "Нет типа",
            genres = title.genres ?: emptyList(),
            rating = title.rating ?: "Не указан рейтинг",
            poster = title.poster ?: "Нет постера"
        )
    }
}