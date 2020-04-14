package com.tosh.fcm.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tosh.fcm.R

class FCMService : FirebaseMessagingService() {

    var CHANNEL_ID = "2"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.notification != null){
            showNotification(remoteMessage.notification!!.title.toString(), remoteMessage.notification!!.body.toString())
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    private fun showNotification(title: String, text:String){

        createNotificationChannel()


        var  builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_chat)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setColor(resources.getColor(R.color.colorAccent))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(2, builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Info 1"
            val descriptionText = "Info 1"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}
