package com.example.apianime.presentation.model

import kotlinx.serialization.Serializable

sealed class PageItem {
    @Serializable
    data class TitleDetail(
        val title: TitleItem
    ) : PageItem()

    @Serializable
    data object FavouritesTitles : PageItem()
}