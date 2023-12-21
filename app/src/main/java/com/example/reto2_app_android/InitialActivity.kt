package com.example.reto2_app_android

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reto2_app_android.databinding.InitialLayoutBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InitialActivity : AppCompatActivity() {
    
    private val scope = MainScope()
    private lateinit var initialLayoutBinding: InitialLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialLayoutBinding = InitialLayoutBinding.inflate(layoutInflater)
        setContentView(initialLayoutBinding.root)

        val loadingBar: ProgressBar = initialLayoutBinding.progressBar
        scope.launch {
            while (loadingBar.progress < loadingBar.max)
                initLoading(loadingBar)
        }

    }

    private suspend fun initLoading(
        progressBar: ProgressBar
    ) {
        delay(2000)
        progressBar.isIndeterminate = false
        progress(progressBar)

    }

    private suspend fun progress(progressBar: ProgressBar) {
        while (progressBar.progress < progressBar.max) {
            delay(300)
            progressBar.incrementProgressBy(PROGRESS_INCREMENT)
        }
        if (isNetworkAvailable(this)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Log.i("Prueba","Entrando")
            finish()
        } else {
            progressBar.progress = 0
            progressBar.secondaryProgress = 0
            progressBar.isIndeterminate = true
            Toast.makeText(this, getString(R.string.noWifi), Toast.LENGTH_SHORT).show()
            delay(2000)
            Toast.makeText(this, getString(R.string.reconnecting), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val PROGRESS_INCREMENT = 5
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }
        }
        return false
    }
}