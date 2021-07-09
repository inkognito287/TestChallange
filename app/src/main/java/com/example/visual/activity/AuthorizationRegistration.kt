package com.example.visual.activity

import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.visual.R
import com.example.visual.fragments.Authorization
import com.example.visual.fragments.Registration

class AuthorizationRegistration : AppCompatActivity() {
    private lateinit var container: FrameLayout


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        setFragmentAuthorization()

        val authorization = findViewById<ToggleButton>(R.id.authorization)
        val registration = findViewById<ToggleButton>(R.id.registration)
        container = findViewById<FrameLayout>(R.id.fragmentContainer)



        authorization.setOnClickListener() {

            setAuthorization()
            authorization.isChecked = true
            registration.isChecked = false

        }
        registration.setOnClickListener() {

            setRegistration()
            authorization.isChecked = false
            registration.isChecked = true

        }
        authorization.isChecked = true
        val password = Authorization().activity?.findViewById<EditText>(R.id.authorizationPassword)



    }

    private fun setFragmentAuthorization() {
        val fragmentAuthorization = Authorization()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentAuthorization)
        fragmentTransaction.commit()

    }






    fun setRegistration() {
        val fragmentRegistration = Registration()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentRegistration)
        fragmentTransaction.commit()

    }

    fun setAuthorization() {
        val fragmentAuthorization = Authorization()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentAuthorization)
        fragmentTransaction.commit()

    }
}