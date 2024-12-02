package com.example.apianime.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BarItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String
){
    data object ListPageModel : BarItem(
        route = "Тайтлы",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        title = "Список"
    )

    data object PersonPageModel : BarItem(
        route = "Профиль",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        title = "Профиль"
    )
}