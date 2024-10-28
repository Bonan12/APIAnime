package com.example.apianime.data.network.mapper

import com.example.apianime.data.network.model.TitleApi
import com.example.apianime.data.storage.model.TitleDb

interface ITitleDbMapper {
    fun invoke(titleApi: TitleApi): TitleDb
}

class TitleDbMapper : ITitleDbMapper {
    override fun invoke(titleApi: TitleApi): TitleDb {
        with(titleApi.data){
            return TitleDb(
                id = this?.malId ?: 0,
                name = this?.title ?: "Не указано название",
                type = this?.type ?: "Не указан тип",
                genres = this?.genres?.map { it.name ?: "Не указан жанр" },
                rating = this?.rating ?: "Не указан возрастной рейтинг",
                poster = this?.images?.jpg?.imageUrl ?: "Не указано изображение"
            )
        }
    }
}