package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("aired")
    val aired: Aired? = null,
    @SerialName("airing")
    val airing: Boolean? = null,
    @SerialName("approved")
    val approved: Boolean? = null,
    @SerialName("background")
    val background: String? = null,
    @SerialName("broadcast")
    val broadcast: Broadcast? = null,
    @SerialName("demographics")
    val demographics: List<Demographic?>? = null,
    @SerialName("duration")
    val duration: String? = null,
    @SerialName("episodes")
    val episodes: Int? = null,
    @SerialName("explicit_genres")
    val explicit_genres: List<ExplicitGenre?>? = null,
    @SerialName("favorites")
    val favorites: Int? = null,
    @SerialName("genres")
    val genres: List<Genre?>? = null,
    @SerialName("images")
    val images: Images? = null,
    @SerialName("licensors")
    val licensors: List<Licensor?>? = null,
    @SerialName("mal_id")
    val mal_id: Int? = null,
    @SerialName("members")
    val members: Int? = null,
    @SerialName("popularity")
    val popularity: Int? = null,
    @SerialName("producers")
    val producers: List<Producer?>? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("rating")
    val rating: String? = null,
    @SerialName("score")
    val score: Double? = null,
    @SerialName("scored_by")
    val scored_by: Int? = null,
    @SerialName("season")
    val season: String? = null,
    @SerialName("source")
    val source: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("studios")
    val studios: List<Studio?>? = null,
    @SerialName("synopsis")
    val synopsis: String? = null,
    @SerialName("themes")
    val themes: List<Theme?>? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("title_english")
    val title_english: String? = null,
    @SerialName("title_japanese")
    val title_japanese: String? = null,
    @SerialName("title_synonyms")
    val title_synonyms: List<String?>? = null,
    @SerialName("titles")
    val titles: List<Title?>? = null,
    @SerialName("trailer")
    val trailer: Trailer? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("year")
    val year: Int? = null
)