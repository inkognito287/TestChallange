package com.example.visual.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.visual.R
import java.net.HttpURLConnection
import java.net.URL

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun authorization(v: View){
        var name =findViewById<EditText>(R.id.editTextTextPersonName)
        val url = URL("https://9b1a30d6f3ac.ngrok.io/Home/Aut")

        Thread(){
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                }
            }
        }}.start()

    }
}