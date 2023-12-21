package com.example.reto2_app_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reto2_app_android.databinding.PruebaLayoutBinding

class PruebaActivity : AppCompatActivity() {

    private lateinit var pruebaLayoutBinding: PruebaLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pruebaLayoutBinding = PruebaLayoutBinding.inflate(layoutInflater)
        setContentView(pruebaLayoutBinding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = Prueba1Fragment()
        fragmentTransaction.add(R.id.authFragmentContainerView, fragment)
        fragmentTransaction.commit()


    }
}