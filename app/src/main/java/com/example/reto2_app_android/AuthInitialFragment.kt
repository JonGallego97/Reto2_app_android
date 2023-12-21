package com.example.reto2_app_android

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthInitialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthInitialFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var AuthInitialFragment : AuthInitialFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_auth_initial, container, false)

        //Set on click Listener here

        // Return the fragment view/layout
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingBar: ProgressBar = view.findViewById(R.id.progressBar4)
        lifecycleScope.launch {
            while (loadingBar.progress < loadingBar.max)
                initLoading(loadingBar)
        }
    }



    suspend fun initLoading(
        progressBar: ProgressBar
    ) {
        delay(2000)
        progressBar.isIndeterminate = false
        progress(progressBar)

    }

    private suspend fun progress(progressBar: ProgressBar) {
        while (progressBar.progress < progressBar.max) {
            delay(300)
            progressBar.incrementProgressBy(InitialActivity.PROGRESS_INCREMENT)
        }
        if (getActivity()?.let { isNetworkAvailable(it) } == true) {
            val newFragment = LoginFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.authFragmentContainerView, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            progressBar.progress = 0
            progressBar.secondaryProgress = 0
            progressBar.isIndeterminate = true
            Toast.makeText(context,getString(R.string.noWifi), Toast.LENGTH_SHORT).show()
            delay(2000)
            Toast.makeText(context,getString(R.string.reconnecting), Toast.LENGTH_SHORT).show()
        }

    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }
        }
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AuthInitialFragment.
         */
        // TODO: Rename and change types and number of parameters

        const val PROGRESS_INCREMENT = 5

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AuthInitialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }


}