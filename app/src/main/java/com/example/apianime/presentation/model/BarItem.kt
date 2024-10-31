package com.example.apianime.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.List
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

    data object SettingPageModel : BarItem(
        route = "Настройки",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        title = "Настройки"
    )
}