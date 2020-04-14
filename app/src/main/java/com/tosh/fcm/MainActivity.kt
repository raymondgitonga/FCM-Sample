package com.tosh.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        buy_btn.setOnClickListener {
           val  numberOfCookies = cookies.text.toString()

            val intent = Intent(this, CookieActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val largeImage = BitmapFactory.decodeResource(resources, R.drawable.cookie)


            var  builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_chat)
                .setContentTitle("Cookies")
                .setContentText("You just bought $numberOfCookies cookies!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setLargeIcon(largeImage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManagerCompat.notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Info"
            val descriptionText = "Info"
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
