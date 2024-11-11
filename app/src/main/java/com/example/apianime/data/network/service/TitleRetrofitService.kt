package com.example.apianime.data.network.service


import com.example.apianime.data.network.model.ListTitlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TitleRetrofitService{
    @GET("anime")
    suspend fun getTitlesByQuery(
        @Query("page") page: Int = 1
    ): ListTitlesResponse
}