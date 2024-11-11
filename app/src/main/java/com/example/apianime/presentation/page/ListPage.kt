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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.apianime.presentation.functionPages.TitlesFunctions
import com.example.apianime.presentation.viewModel.ListPageViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPage(
    funcs: TitlesFunctions,
    viewModel: ListPageViewModel = koinViewModel()
) {
    val items by viewModel.titles.collectAsState()
    val sorted by viewModel.isIncreased.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Аниме"
                    )
                },
                    actions = {
                        IconButton(onClick = { funcs.goToFavourites() }) {
                            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
                        }
                        TextButton(modifier = Modifier.padding(),
                            onClick = {
                                viewModel.changeSorting()
                            }
                        ) {
                            Text(text = if (sorted)"По убыванию" else "По возрастанию")
                        }
                    })
            }
        ) { innerPadding ->
            if (items.isNotEmpty()) {
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
                                        IconButton(onClick = {
                                            viewModel.saveDatabase(items[index])
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.FavoriteBorder,
                                                contentDescription = null
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                )
            } else {
                Column(modifier = Modifier.padding(innerPadding)) {
                    Text("Попробуй ещё раз")
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.refresh() },
                        imageVector = Icons.Default.Refresh, contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ListPagePreview() {
    ListPage(funcs = TitlesFunctions(rememberNavController()), viewModel = viewModel())
}