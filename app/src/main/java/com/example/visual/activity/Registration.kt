package com.example.visual.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        val name =findViewById<EditText>(R.id.editTextTextPersonName)
        val password= findViewById<EditText>(R.id.editTextTextPersonPassword)
        val url = URL("https://ea4830e3da13.ngrok.io/Home/Check?Name=${name.getText()}&Password=${password.getText()}")

        Thread(){
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
            var string=""
            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                   string=line
                }
               runOnUiThread(){
                   if (string=="true")
                       Toast.makeText(this@Registration, "Вы авторизованы", Toast.LENGTH_SHORT).show()
                   else
                       Toast.makeText(this@Registration, "Проверьте введённые данные", Toast.LENGTH_SHORT).show()
               }
            }
        }}.start()


    }
}