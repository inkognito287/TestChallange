package com.example.visual.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.visual.MyDialogFragment
import com.example.visual.R
import com.example.visual.databinding.FragmentRegistrationBinding
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern


class Registration : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    var pattern: String = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,40}\$"
    var pat = Pattern.compile(pattern)
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
        // binding.registrationLogin
        binding.button2.setOnClickListener {
            //check()
            //if (passwordValidation())
                emailVerification()
                    // registration()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun registration() {

        binding.registrationLogin.clearFocus()
        binding.registrationPassword.clearFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val layout = activity?.findViewById<ConstraintLayout>(R.id.main)
        imm.hideSoftInputFromWindow(layout?.windowToken, 0)
        val url =
            URL("http://7c06714a6639.ngrok.io/Home/Add?Name=${binding.registrationLogin.text}&Password=${binding.registrationPassword.text}")
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

                        if (string == "true") {
                            Toast.makeText(
                                activity,
                                "Регистрация произведена успешно",
                                Toast.LENGTH_SHORT
                            ).show()
                            val myDialogFragment = MyDialogFragment()
                            val manager: FragmentManager = requireActivity().supportFragmentManager
                            val transaction: FragmentTransaction = manager.beginTransaction()
                            myDialogFragment.show(transaction, "dialog")
                            Log.d("MyLog", "Регистрация произведена успешно")
                            val toggleButton =
                                requireActivity().findViewById<ToggleButton>(R.id.authorization)
                            toggleButton.performClick()

                        } else {
                            progress?.visibility = View.GONE
                            Toast.makeText(
                                activity,
                                "Пользователь с таким именем уже существует",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("MyLog", "Пользователь с таким именем уже существует")
                        }
                        progress?.visibility = View.GONE
                    })
                }
            }
        }.start()


    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun emailVerification() {
        val number = (1000..9999).random()
        val url =
            URL("http://7c06714a6639.ngrok.io/Home/Email?email=${binding.email.text}&number=${number}")
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

                        if (string == "true") {
                            Log.d("MyLog", "email Отправлен")


                        }
                        else{
                            Log.d("MyLog", "email Неотправлен")
                        }
                    })
                }
            }
        }.start()
    }

    private fun check() {
        if (binding.registrationPassword.text.isEmpty())
            Log.d("MyLog", "empty")
    }

    @SuppressLint("ResourceAsColor")
    private fun passwordValidation(): Boolean {
        if (binding.registrationLogin.text.isEmpty()) {
            Log.d("MyLog", "empty")
            return false

        }
        if (binding.registrationPassword.text.isEmpty()) {
            Log.d("MyLog", "empty")
            return false
        }
        if (binding.email.text.isEmpty()) {
            Log.d("MyLog", "empty")
            return false
        }
        val matcher = pat.matcher(binding.registrationPassword.text.toString())
        if (!matcher.matches()) {
            Log.d("MyLog", "Пароль не удовлетворяет требованиям ")

            binding.registrationPassword.setHintTextColor(Color.RED)
            // binding.repeatPassword.setHintTextColor(Color.RED)
            binding.registrationPassword.setText("")
            binding.repeatPassword.setText("")
            binding.registrationPassword.requestFocus()
            return false
        }

        if (binding.registrationPassword.getText()
                .toString() != binding.repeatPassword.getText().toString()
        ) {
            Log.d(
                "MyLog",
                "not equal ${binding.registrationPassword.text}, ${binding.repeatPassword.text}"
            )
            return false

        }
        return true

    }

}

