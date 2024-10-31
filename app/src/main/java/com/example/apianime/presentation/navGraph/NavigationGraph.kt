package com.example.apianime.presentation.navGraph

import MyNavType
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.apianime.presentation.functionPages.DetailFunctions
import com.example.apianime.presentation.functionPages.TitlesFunctions
import com.example.apianime.presentation.model.BarItem
import com.example.apianime.presentation.model.PageItem
import com.example.apianime.presentation.model.TitleItem
import com.example.apianime.presentation.page.ListPage
import com.example.apianime.presentation.page.SettingPage
import com.example.apianime.presentation.page.TitleDetailPage
import kotlin.reflect.typeOf

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BarItem.ListPageModel.route
    ) {
        composable(BarItem.ListPageModel.route) {
            ListPage(funcs = TitlesFunctions(navController = navController))
        }
        composable(BarItem.SettingPageModel.route) {
            SettingPage()
        }
        composable<PageItem.TitleDetail>(
            typeMap = mapOf(typeOf<TitleItem>() to MyNavType)
        ) { backStackEntry ->
            val title = backStackEntry.toRoute<PageItem.TitleDetail>()
            TitleDetailPage(
                title = TitleItem(
                    name = title.title.name,
                    type = title.title.type,
                    genres = title.title.genres,
                    rating = title.title.rating,
                    poster = Uri.parse(title.title.poster.toString().replace("***", "/"))
                ),
                funcs = DetailFunctions(navController)
            )
        }
    }
}