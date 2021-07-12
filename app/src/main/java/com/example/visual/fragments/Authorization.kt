package com.example.visual.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.visual.R
import com.example.visual.activity.MainActivity
import com.example.visual.databinding.FragmentAuthorizationBinding
import java.net.HttpURLConnection
import java.net.URL


class Authorization : Fragment() {
    lateinit var saveVariable: SharedPreferences
    lateinit var binding: FragmentAuthorizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveVariable =
            requireActivity().getSharedPreferences("Passwords", AppCompatActivity.MODE_PRIVATE)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authorizationButton.setOnClickListener {
            if (passwordLoginValidation())
            authorization()
        }

        val checkBoxState = saveVariable.getBoolean("CheckBox", false)
        if (checkBoxState) {
            binding.authorizationLogin.setText(saveVariable.getString("login", ""))
            binding.authorizationPassword.setText(saveVariable.getString("password", ""))
            binding.checkBox.isChecked = true

        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun authorization() {


        binding.authorizationLogin.clearFocus()
        binding.authorizationPassword.clearFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val layout = activity?.findViewById<ConstraintLayout>(R.id.main)
        imm.hideSoftInputFromWindow(layout?.windowToken, 0)
        val url =
            URL("http://7c06714a6639.ngrok.io/Home/Check?Name=${binding.authorizationLogin.text}&Password=${binding.authorizationPassword.text}")
        val progress = activity?.findViewById<ProgressBar>(R.id.progressBaraAuth)
        progress?.visibility = View.VISIBLE
        Thread {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"  // optional default is GET

                println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
                var string = ""
                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        string = line
                    }
                    activity?.runOnUiThread(Runnable {
                        println(string)
                        if (string == "true") {
                            if (binding.checkBox.isChecked == true) {
                                saveLogin()
                                savePassword()
                                saveCheckBoxStateTrue()
                            } else {
                                saveCheckBoxStateFalse()
                            }

                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()


                        } else {
                            progress?.visibility = View.GONE
                            Toast.makeText(
                                activity,
                                "Проверьте введённые данные",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                }
            }
        }.start()


    }
    private fun passwordLoginValidation(): Boolean {
        return !(binding.authorizationPassword.text.isEmpty() or binding.authorizationLogin.text.isEmpty())
    }

    fun savePassword() {


        val edit = saveVariable.edit()
        edit.putString("password", binding.authorizationPassword.text.toString())
        edit.apply()
    }

    private fun saveCheckBoxStateTrue() {
        val edit = saveVariable.edit()
        edit.putBoolean("CheckBox", true)
        edit.apply()

    }

    private fun saveCheckBoxStateFalse() {
        val edit = saveVariable.edit()
        edit.putBoolean("CheckBox", false)
        edit.apply()

    }

    private fun saveLogin() {

        val edit = saveVariable.edit()
        edit.putString("login", binding.authorizationLogin.text.toString())
        edit.apply()
    }
}