package com.tosh.fcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class CookieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cookie)

        val cookie = intent.getStringExtra("cookie")

        Toast.makeText(this, cookie, Toast.LENGTH_LONG).show()
    }
}
