package com.example.apianime.presentation.page

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.apianime.presentation.functionPages.DetailFunctions
import com.example.apianime.presentation.model.TitleItem
import com.example.apianime.presentation.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleDetailPage(
    funcs: DetailFunctions,
    title: TitleItem
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Описание"
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { funcs.onToBack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(innerPadding),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = title.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = title.type,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = title.genres.joinToString(", "),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = "Рейтинг: ${title.rating}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Image(
                        painter = rememberAsyncImagePainter(title.poster.toString()),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 8.dp, bottom = 10.dp),
                        contentScale = ContentScale.Crop,

                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun TitleDetailPagePreview() {
    TitleDetailPage(
        funcs = DetailFunctions(rememberNavController()),
        title = TitleItem(
            "название",
            "тип",
            listOf("Жанр1", "Жанр2"),
            "18+",
            Uri.parse("https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3d0978ff-54f8-4cc2-b495-8c9dbd4ba708/1920x")
        )
    )
}