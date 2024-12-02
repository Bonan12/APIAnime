package com.example.apianime.data.network.mapper

import com.example.apianime.data.network.model.Data
import com.example.apianime.data.storage.model.TitleDb

interface ITitleDbMapper {
    fun invoke(data: Data?): TitleDb
}

class TitleDbMapper : ITitleDbMapper {
    override fun invoke(data: Data?): TitleDb {
        return with(data){
            TitleDb(
                id = this?.mal_id ?: 0,
                name = this?.title,
                type = this?.type,
                genres = this?.genres?.map { it?.name ?: "" },
                rating = this?.rating,
                poster = this?.images?.jpg?.image_url
            )
        }
    }
}