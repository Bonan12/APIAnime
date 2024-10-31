package com.example.apianime.presentation.mapper

import android.net.Uri
import com.example.apianime.domain.model.Title
import com.example.apianime.presentation.model.TitleItem

interface ITitleItemMapper {
    fun invoke(title: Title) : TitleItem
}

class TitleItemMapper():
    ITitleItemMapper {
    override fun invoke(title: Title): TitleItem {
        return TitleItem(
            name = title.name,
            type = title.type,
            genres = title.genres,
            rating = title.rating,
            poster = Uri.parse(title.poster.replace("/","***"))
        )
    }

}