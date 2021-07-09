package com.example.visual.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.visual.R
import com.example.visual.databinding.FragmentRegistrationBinding
import java.net.HttpURLConnection
import java.net.URL


class Registration : Fragment() {
    lateinit var binding:FragmentRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationLogin
        binding.button2.setOnClickListener(){
            registration()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun registration(){

            binding.registrationLogin.clearFocus()
            binding.registrationPassword.clearFocus()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val layout = activity?.findViewById<ConstraintLayout>(R.id.main)
            imm.hideSoftInputFromWindow(layout?.getWindowToken(), 0)
            val url =
                URL("https://381669a1e512.ngrok.io/Home/Add?Name=${binding.registrationLogin.getText()}&Password=${binding.registrationPassword.getText()}")
            val progress = activity?.findViewById<ProgressBar>(R.id.progressBaraAuth)
            progress?.visibility = View.VISIBLE
            Thread() {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"  // optional default is GET

                    println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
                    var string = ""
                    inputStream.bufferedReader().use {
                        it.lines().forEach { line ->
                            string = line
                        }
                        activity?.runOnUiThread(Runnable() {

                            if (string == "true") {
                                Toast.makeText(activity, "Регистрация произведена успешно", Toast.LENGTH_SHORT).show()
                                Log.d("MyLog","Регистрация произведена успешно")


                            } else {
                                progress?.visibility = View.GONE
                                Toast.makeText(
                                    activity,
                                    "Пользователь с таким именем уже существует",
                                    Toast.LENGTH_SHORT
                                ).show()
                              Log.d("MyLog","Пользователь с таким именем уже существует")
                            }
                            progress?.visibility=View.GONE
                        })
                    }
                }
            }.start()


        }

    }

