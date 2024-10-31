package com.example.apianime.presentation.navGraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apianime.presentation.model.BarItem

@Composable
fun NavigationGraphBar(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val items = listOf(
            BarItem.ListPageModel,
            BarItem.SettingPageModel
        )

        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            label = { Text(text = item.title) },
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                if (index == selectedItemIndex) Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = null
                                ) else Icon(
                                    imageVector = item.unselectedIcon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Preview
@Composable
fun NavigationGraphBarPreview() {
    NavigationGraph(navController = rememberNavController())
}