package com.example.finalexamapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.finalexamapp.R // Assurez-vous que cette importation est présente

class NotificationHelper {
    fun createNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "exam_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Exam Notifications", NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for exam notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("FinalExamApp Notification")
            .setContentText("Bonjour, voici un exemple de notification.")
            .setSmallIcon(R.drawable.ic_notification) // Assurez-vous que cette icône existe
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}
