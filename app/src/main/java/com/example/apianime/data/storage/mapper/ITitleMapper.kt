package com.example.apianime.data.storage.mapper

import com.example.apianime.data.storage.model.TitleDb
import com.example.apianime.domain.model.Title

interface ITitleMapper{
    fun toDomain(titleDb: TitleDb) : Title

    fun toDb(title: Title) : TitleDb
}

class TitleMapper() : ITitleMapper {
    override fun toDomain(titleDb: TitleDb): Title {
        return Title(
            name = titleDb.name ?: "",
            type = titleDb.type ?: "",
            genres = titleDb.genres ?: emptyList(),
            rating = titleDb.rating ?: "",
            poster = titleDb.poster ?: ""
        )
    }

    override fun toDb(title: Title): TitleDb {
        return TitleDb(
            id = 0,
            name = title.name,
            type = title.type,
            genres = title.genres,
            rating = title.rating,
            poster = title.poster
        )
    }
}