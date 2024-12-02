package com.example.apianime.presentation.functionPages

import androidx.navigation.NavHostController
import com.example.apianime.presentation.model.PageItem
import com.example.apianime.presentation.model.TitleItem

/**
 * @author Evgenii
 */
class FavouritesFunctions(
    private val navController: NavHostController
) {
    fun goToDetail(title: TitleItem){
        navController.navigate(PageItem.TitleDetail(title = title))
    }
}