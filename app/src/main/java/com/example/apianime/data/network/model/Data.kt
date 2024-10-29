package com.example.apianime.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("aired")
    val aired: Aired? = Aired(),
    @SerialName("airing")
    val airing: Boolean? = false,
    @SerialName("approved")
    val approved: Boolean? = false,
    @SerialName("background")
    val background: String? = "",
    @SerialName("broadcast")
    val broadcast: Broadcast? = Broadcast(),
    @SerialName("demographics")
    val demographics: List<Demographic>? = listOf(),
    @SerialName("duration")
    val duration: String? = "",
    @SerialName("episodes")
    val episodes: Int? = 0,
    @SerialName("explicit_genres")
    val explicit_genres: List<ExplicitGenre>? = listOf(),
    @SerialName("external")
    val `external`: List<External>? = listOf(),
    @SerialName("favorites")
    val favorites: Int? = 0,
    @SerialName("genres")
    val genres: List<Genre>? = listOf(),
    @SerialName("images")
    val images: Images? = Images(),
    @SerialName("licensors")
    val licensors: List<Licensor>? = listOf(),
    @SerialName("mal_id")
    val mal_id: Int? = 0,
    @SerialName("members")
    val members: Int? = 0,
    @SerialName("popularity")
    val popularity: Int? = 0,
    @SerialName("producers")
    val producers: List<Producer>? = listOf(),
    @SerialName("rank")
    val rank: Int? = 0,
    @SerialName("rating")
    val rating: String? = "",
    @SerialName("relations")
    val relations: List<Relation>? = listOf(),
    @SerialName("score")
    val score: Double? = 0.0,
    @SerialName("scored_by")
    val scored_by: Int? = 0,
    @SerialName("season")
    val season: String? = "",
    @SerialName("source")
    val source: String? = "",
    @SerialName("status")
    val status: String? = "",
    @SerialName("streaming")
    val streaming: List<Streaming>? = listOf(),
    @SerialName("studios")
    val studios: List<Studio>? = listOf(),
    @SerialName("synopsis")
    val synopsis: String? = "",
    @SerialName("theme")
    val theme: Theme? = Theme(),
    @SerialName("themes")
    val themes: List<ThemeX>? = listOf(),
    @SerialName("title")
    val title: String? = "",
    @SerialName("title_english")
    val title_english: String? = "",
    @SerialName("title_japanese")
    val title_japanese: String? = "",
    @SerialName("title_synonyms")
    val title_synonyms: List<String>? = listOf(),
    @SerialName("titles")
    val titles: List<Title>? = listOf(),
    @SerialName("trailer")
    val trailer: Trailer? = Trailer(),
    @SerialName("type")
    val type: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("year")
    val year: Int? = 0
)