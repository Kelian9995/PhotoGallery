package com.bignerdranch.android.photogallery

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager

private const val TAG  = "NotificationReceiver"

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"received result: $resultCode")
        if (resultCode != Activity.RESULT_OK) {
            // Активность переднего плана отменила возврат трансляции
        }
        val requestCode = intent.getIntExtra(PollWorker.REQUEST_CODE, 0)
        val notification: Notification =
            // Добавил !! для утверждения что  PollWorker.NOTIFICATION не null
            intent.getParcelableExtra(PollWorker.NOTIFICATION)!!
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(requestCode, notification)
    }
}