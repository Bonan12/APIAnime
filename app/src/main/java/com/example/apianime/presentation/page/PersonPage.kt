@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apianime.presentation.page

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import com.example.apianime.R
import com.example.apianime.presentation.model.BarItem
import com.example.apianime.presentation.viewModel.PersonPageViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonPage(
    onToEdit: () -> Unit,
    viewModel: PersonPageViewModel = koinViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    SystemBroadcastReceiver(
        systemAction = ACCESS,
        onSystemEvent = { intent ->
            if (intent?.action == ACCESS) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
                if (id != -1L) {
                    viewModel.open(context)
                }
            }
        }
    )


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = BarItem.PersonPageModel.title
                    )
                },
                    actions = {
                        IconButton(
                            onClick = { onToEdit() }
                        ) {
                            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                        }
                    })
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePictureButton(
                    photoUrl = state.photoUri,
                    onToEdit = {
                        onToEdit()
                    })

                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoSection(state.name, state.role)

                Spacer(modifier = Modifier.height(24.dp))

                ResumeButton(onClickDownload = {
                    viewModel.onClickLink(context = context)
                })
            }
        }
    }
}

@Composable
fun ProfilePictureButton(
    photoUrl: Uri?,
    onToEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp),
            error = painterResource(R.drawable.error)
        )
    }
}

@Composable
fun ProfileInfoSection(
    name: String,
    description: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
        )
    }
}

@Composable
fun ResumeButton(
    onClickDownload: () -> Unit
) {
    Button(
        onClick = {
            onClickDownload()
        },
        modifier = Modifier.width(200.dp),
    ) {
        Text(
            text = "Нажми на меня",
        )
    }
}

private const val ACCESS = "android.intent.action.DOWNLOAD_COMPLETE"


@Preview(showBackground = true)
@Composable
fun PersonPagePreview() {
    PersonPage({})
}


@Composable
private fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }

        ContextCompat.registerReceiver(
            context, broadcast, intentFilter,
            ContextCompat.RECEIVER_EXPORTED
        )

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}