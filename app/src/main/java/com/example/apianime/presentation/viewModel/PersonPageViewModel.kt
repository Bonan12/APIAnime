package com.example.apianime.presentation.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apianime.domain.usecase.IPersonUseCase
import com.example.apianime.presentation.mapper.IPersonItemMapper
import com.example.apianime.presentation.state.PersonPageState
import kotlinx.coroutines.launch
import java.io.File

class PersonPageViewModel(
    private val mapper: IPersonItemMapper,
    private val useCase: IPersonUseCase
) : ViewModel() {
    private var _state = MutablePersonPageState()
    val state = _state as PersonPageState

    init {
        load()
    }

    fun onClickLink(
        context: Context
    ) {
        val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(state.link))
        with(request) {
            setTitle("sample")
            setMimeType("pdf")
            setDescription("download...")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "sample.pdf"
            )
        }
        val manager: DownloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    fun open(context: Context){
        try {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ),
                "sample.pdf"
            )
            val uri = FileProvider.getUriForFile(
                context,
                context.applicationContext?.packageName + ".provider",
                file
            )
            val intent =
                Intent(Intent.ACTION_VIEW)
            with(intent) {
                setDataAndType(
                    uri,
                    "application/pdf"
                )
                flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun load() {
        viewModelScope.launch {
            useCase.get().collect {
                mapper.toUi(it).apply {
                    _state.name = name
                    _state.role = role
                    _state.photoUri = photoUri
                }
            }
        }
    }

    private class MutablePersonPageState : PersonPageState {
        override var name: String by mutableStateOf("")
        override var role: String by mutableStateOf("")
        override val link: String by mutableStateOf("https://pdfobject.com/pdf/sample.pdf")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
    }
}