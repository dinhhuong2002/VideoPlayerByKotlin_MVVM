package com.example.videoplayerbykotlin

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.media3.ui.PlayerNotificationManager
import com.example.DhPlayerView

class MyForegroundService: Service() {

    private lateinit var context:Context
    private lateinit var dhPlayerView: DhPlayerView
    private lateinit var playerNotificationManager: PlayerNotificationManager
    private val CHANNEL_ID = "MyForegroundServiceChannel"
    private val NOTIFICATION_ID = 1

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        dhPlayerView = DhPlayerView(context,null,null)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("ExoPlayer Foreground Service")
            .setContentText("Playing video in the foreground")
            .setSmallIcon(R.drawable.icon_app)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

}