package com.example.reto2_app_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.example.reto2_app_android.databinding.ChangePasswordLayoutBinding
import com.example.reto2_app_android.databinding.LoginLayoutBinding
import com.example.reto2_app_android.databinding.RegisterLayoutBinding

class RegisterActivity : AppCompatActivity(R.layout.check_login_layout) {

    private lateinit var changePasswordLayoutBinding: ChangePasswordLayoutBinding
    private lateinit var registerLayoutBinding: RegisterLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerLayoutBinding = RegisterLayoutBinding.inflate(layoutInflater)

        changePasswordLayoutBinding = ChangePasswordLayoutBinding.inflate(layoutInflater)
        setContentView(changePasswordLayoutBinding.root)



        changePasswordLayoutBinding.changePasswordButton.setOnClickListener() {
            setContentView(registerLayoutBinding.root)
        }



    }
}