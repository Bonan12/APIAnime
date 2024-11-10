package com.example.apianime.di

import com.example.apianime.data.network.service.TitleRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory<Retrofit> { provideRetrofit() }
    single<TitleRetrofitService> { provideNetworkApi(get()) }
}

fun provideRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return Retrofit.Builder()
        .baseUrl(BASE_URL_KEY)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
        .build()

}

private const val BASE_URL_KEY = "https://api.jikan.moe/v4/"

fun provideNetworkApi(retrofit: Retrofit): TitleRetrofitService =
    retrofit.create(TitleRetrofitService::class.java)