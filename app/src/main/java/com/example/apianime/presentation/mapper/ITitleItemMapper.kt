package com.example.apianime.presentation.mapper

import android.net.Uri
import com.example.apianime.domain.model.Title
import com.example.apianime.presentation.model.TitleItem

interface ITitleItemMapper {
    fun toUi(title: Title) : TitleItem

    fun toDomain(titleItem: TitleItem) : Title
}

class TitleItemMapper:
    ITitleItemMapper {
    override fun toUi(title: Title): TitleItem {
        return TitleItem(
            name = title.name.ifEmpty { "Нет названия" },
            type = title.type.ifEmpty { "Нет типа" },
            genres = title.genres.ifEmpty { emptyList() },
            rating = title.rating.ifEmpty { "Нет рейтинга" },
            poster = Uri.parse(title.poster.replace("/","***"))
        )
    }

    override fun toDomain(titleItem: TitleItem) : Title {
        return Title(
            name = titleItem.name,
            type = titleItem.type,
            genres = titleItem.genres,
            rating = titleItem.rating,
            poster = titleItem.poster.toString().replace("***","/")
        )
    }

}