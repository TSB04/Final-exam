package com.example.finalexamapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.finalexamapp.R
import com.google.android.material.snackbar.Snackbar

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val taskId = intent?.getIntExtra("TASK_ID", -1) ?: return
        val notificationHelper = NotificationHelper()
        notificationHelper.createNotification(context, context.getString(R.string.task_reminder), context.getString(R.string.task_due))

        // Show Snackbar (if possible in the current context)
    }
}
