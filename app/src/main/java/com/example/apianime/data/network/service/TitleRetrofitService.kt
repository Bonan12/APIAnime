package com.example.apianime.data.network.service


import com.example.apianime.data.network.model.Data
import com.example.apianime.data.network.model.TitleApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TitleRetrofitService{
    @GET("anime/{id}/full")
    suspend fun getTitleById(
        @Path("id") id: Int
    ) : TitleApi

//    @GET("anime")
//    suspend fun getTitlesByQuery(
//        @Query("q") query: String = "d",
//        @Query("limit") limit: Int = 5
//    ): ListTitlesApi

    @GET("random/anime")
    suspend fun getRandomAnime(
    ): TitleApi
}