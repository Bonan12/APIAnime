package com.example.apianime.data.network.mapper

import com.example.apianime.data.network.model.TitleApi
import com.example.apianime.data.storage.model.TitleDb

interface ITitleDbMapper {
    fun invoke(titleApi: TitleApi): TitleDb
}

class TitleDbMapper : ITitleDbMapper {
    override fun invoke(titleApi: TitleApi): TitleDb {
        return with(titleApi.data){
            TitleDb(
                id = this?.mal_id ?: 0,
                name = this?.title,
                type = this?.type,
                genres = this?.genres?.map { it.name ?: ""},
                rating = this?.rating,
                poster = this?.images?.jpg?.image_url
            )
        }
    }
}