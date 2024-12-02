package com.example.apianime.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.NotificationManagerCompat
import com.example.apianime.app.APIAnimeApp
import com.example.apianime.presentation.receiver.BroadcastChannel
import com.example.apianime.presentation.theme.APIAnimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val broadcastChannel: BroadcastChannel by lazy {
            BroadcastChannel(NotificationManagerCompat.from(this), this)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        broadcastChannel.createNotificationChannels()
        setContent {
            APIAnimeTheme {
                APIAnimeApp().CreateNavigation()
            }
        }
    }
}
