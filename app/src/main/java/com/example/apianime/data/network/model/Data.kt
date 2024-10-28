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
    val background: String? = null,
    @SerialName("broadcast")
    val broadcast: Broadcast? = Broadcast(),
    @SerialName("demographics")
    val demographics: List<Demographic>? = listOf(),
    @SerialName("duration")
    val duration: String? = null,
    @SerialName("episodes")
    val episodes: Int? = null,
    @SerialName("explicit_genres")
    val explicitGenres: List<ExplicitGenre>? = listOf(),
    @SerialName("external")
    val `external`: List<External>? = listOf(),
    @SerialName("favorites")
    val favorites: Int? = null,
    @SerialName("genres")
    val genres: List<Genre>? = listOf(),
    @SerialName("images")
    val images: Images? = Images(),
    @SerialName("licensors")
    val licensors: List<Licensor>? = listOf(),
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("members")
    val members: Int? = null,
    @SerialName("popularity")
    val popularity: Int? = null,
    @SerialName("producers")
    val producers: List<Producer>? = listOf(),
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("rating")
    val rating: String? = null,
    @SerialName("relations")
    val relations: List<Relation>? = listOf(),
    @SerialName("score")
    val score: Double? = null,
    @SerialName("scored_by")
    val scoredBy: Int? = null,
    @SerialName("season")
    val season: String? = null,
    @SerialName("source")
    val source: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("streaming")
    val streaming: List<Streaming>? = listOf(),
    @SerialName("studios")
    val studios: List<Studio>? = listOf(),
    @SerialName("synopsis")
    val synopsis: String? = null,
    @SerialName("theme")
    val theme: Theme? = Theme(),
    @SerialName("themes")
    val themes: List<ThemeX>? = listOf(),
    @SerialName("title")
    val title: String? = null,
    @SerialName("title_english")
    val titleEnglish: String? = null,
    @SerialName("title_japanese")
    val titleJapanese: String? = null,
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>? = listOf(),
    @SerialName("titles")
    val titles: List<Title>? = listOf(),
    @SerialName("trailer")
    val trailer: Trailer? = Trailer(),
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("year")
    val year: Int? = null
)