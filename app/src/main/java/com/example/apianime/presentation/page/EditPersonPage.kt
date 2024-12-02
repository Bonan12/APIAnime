@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apianime.presentation.page

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil3.compose.AsyncImage
import com.example.apianime.R
import com.example.apianime.presentation.viewModel.EditPersonPageViewModel
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.util.Date

@Composable
fun EditPersonPage(
    viewModel: EditPersonPageViewModel = koinViewModel(),
    onToBack: () -> Unit
) {
    val viewState = viewModel.state
    val context = LocalContext.current
    var photoUrl by remember { mutableStateOf<Uri?>(null) }

    val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            viewModel.updatePhoto(uri ?: Uri.EMPTY)
        }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                val dialog = AlertDialog.Builder(context)
                    .setMessage("Неправильно")
                    .setCancelable(false)
                    .setPositiveButton("OK") { _, _ ->
                        onToBack()
                    }
                dialog.show()
            }
            viewModel.onClosePermission()
        }

    val mGetContent = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            viewModel.updatePhoto(photoUrl ?: Uri.EMPTY)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onToBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = "Редактирование"
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.save({ onToBack() })
                    }) {
                        Icon(imageVector = Icons.Filled.Save, contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = viewState.photoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(128.dp)
                    .clip(CircleShape)
                    .clickable { viewModel.onSelectPhoto() },
                error = painterResource(R.drawable.error)
            )

            CustomEditText(
                text = viewState.name,
                onTextChange = {
                    viewModel.updateName(it)
                },
                label = "ФИО"
            )
            DropdownMenuBox(
                items = EditPersonRole.entries.map { it.naming },
                label = "Роли",
                onTextChange = {viewModel.updateRole(it)}
            )
        }
    }
    if (viewState.isNeedToShowPermission) {
        LaunchedEffect(Unit) {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }

    fun onCameraSelected() {
        val baseDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
        photoUrl = FileProvider.getUriForFile(
            context,
            context.packageName + ".provider",
            pictureFile
        )
        photoUrl?.let { mGetContent.launch(it) }
    }
    if (viewState.isNeedToShowSelect) {
        Dialog(onDismissRequest = { viewModel.onSelectDismiss() }) {
            Surface(
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Камера",
                        Modifier.clickable {
                            onCameraSelected()
                            viewModel.onSelectDismiss()
                        }
                    )
                    Text(text = "Галерея",
                        Modifier.clickable {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            viewModel.onSelectDismiss()
                        })
                }
            }
        }
    }
}

@Composable
private fun CustomEditText(
    text: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = label, fontSize = 15.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownMenuBox(
    items: List<String>,
    label: String,
    onTextChange: (String) -> Unit,
    isError: Boolean = false,
    error: String = ""
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("")
    }

    Box {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .menuAnchor(),
                value = selectedItem,
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                onValueChange = {
                },
                placeholder = {
                    Text(
                        text = label, fontSize = 15.sp
                    )
                },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit, contentDescription = null
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedItem = item
                            onTextChange(selectedItem)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

private enum class EditPersonRole(val naming: String) {
    EXPERT(naming = "Тайтл-эксперт"),
    BEGINNER(naming = "Начинающий")
}

@Preview
@Composable
private fun EditPersonPagePreview() {
    EditPersonPage(
        onToBack = {}
    )
}