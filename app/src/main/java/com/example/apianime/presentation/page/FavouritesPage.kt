package com.example.apianime.presentation.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.apianime.presentation.functionPages.FavouritesFunctions
import com.example.apianime.presentation.viewModel.FavouritesPageViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * @author Evgenii
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesPage(
    funcs: FavouritesFunctions,
    viewModel: FavouritesPageViewModel = koinViewModel()
) {
    val items by viewModel.titles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Избранные"
                        )
                    },
                )
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(192.dp),
                modifier = Modifier
                    .padding(innerPadding),
                content = {
                    items(items.size) { index ->
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            onClick = { funcs.goToDetail(title = items[index]) }
                        ) {
                            Row {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .height(200.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = items[index].name,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = items[index].genres.joinToString(separator = " ") { it },
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun FavouritesPagePreview() {
    FavouritesPage(funcs = FavouritesFunctions(navController = rememberNavController()))
}