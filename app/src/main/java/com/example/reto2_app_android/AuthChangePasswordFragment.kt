package com.example.reto2_app_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthChangePasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthChangePasswordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_auth_change_password, container, false)

        val changePasswordButton : Button = view.findViewById(R.id.changePasswordButton2)
        changePasswordButton.setOnClickListener() {
            val newFragment = AuthChangePasswordFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(com.example.reto2_app_android.R.id.authFragmentContainerView, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val backButton : Button = view.findViewById(R.id.backButtonChangePassword)
        backButton.setOnClickListener() {
            val newFragment = AuthUserConfirmationFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(com.example.reto2_app_android.R.id.authFragmentContainerView, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AuthChangePasswordFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AuthChangePasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}