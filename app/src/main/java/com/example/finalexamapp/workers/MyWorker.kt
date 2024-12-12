package com.example.finalexamapp.workers

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Log the execution of the background task
        Log.d("MyWorker", "Background task executed")
        return Result.success()
    }

    companion object {
        // Function to schedule the work
        fun scheduleWork(context: Context) {
            // Create a one-time work request
            val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()

            // Enqueue the work request
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }
}
