package com.example.apianime.presentation.functionPages

import androidx.navigation.NavHostController

class DetailFunctions(private val navController: NavHostController) {
    fun onToBack(){
        navController.popBackStack()
    }
}