package com.example.kotlingptjetpack.ui.main

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.util.Log

class LogWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Log a message
        Log.d("LogWorker", "This is a log message from WorkManager")
        return Result.success()
    }
}
