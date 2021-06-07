package com.example.visual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var intent= Intent(this@splashScreen,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}