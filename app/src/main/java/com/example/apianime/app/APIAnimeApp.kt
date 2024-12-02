package com.example.apianime.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.apianime.di.dbModule
import com.example.apianime.di.networkModule
import com.example.apianime.di.rootModule
import com.example.apianime.presentation.navGraph.NavigationGraphBar
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class APIAnimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@APIAnimeApp)
            modules(rootModule, networkModule, dbModule)
        }
    }

    @Composable
    fun CreateNavigation() {
        val navController = rememberNavController()
        NavigationGraphBar(navController = navController)
    }
}