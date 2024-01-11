package com.example.reto2_app_android

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.reto2_app_android.databinding.CheckLoginLayoutBinding
import com.example.reto2_app_android.databinding.LoginLayoutBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var loginLayoutBinding: LoginLayoutBinding
    private lateinit var checkLoginLayoutBinding: CheckLoginLayoutBinding
    private lateinit var sessionRegister: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLoginLayoutBinding = CheckLoginLayoutBinding.inflate(layoutInflater)

        loginLayoutBinding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(loginLayoutBinding.root)


        loginLayoutBinding.loginButton.setOnClickListener() {
            if (sessionRegister.isNotEmpty()) {
                Log.i("Login Activity", "Register")
            } else {
                Log.i("Login Activity", "Login")
            }
        }

        loginLayoutBinding.registerButton.setOnClickListener() {
            setContentView(checkLoginLayoutBinding.root)
            checkLoginLayoutBinding.checkLoginUsername.setText(loginLayoutBinding.loginUsername.text.toString())
            checkLoginLayoutBinding.checkLoginPassword.setText(loginLayoutBinding.loginPassword.text.toString())
        }

        loginLayoutBinding.resetPasswordButton.setOnClickListener() {
            Log.i("Login Activity", "Reset Password")
        }

        loginLayoutBinding.loginRememberMe.setOnClickListener() {

            Log.i("Login Activity", "Reset Password")
        }

        checkLoginLayoutBinding.loginButton.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}