package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.network.RetrofitClient.apiService
import com.example.zeebooks.databinding.FragmentLoginBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment :BaseFragment<FragmentLoginBinding>() {
    override val resId = R.layout.fragment_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            noAccount.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            registerText.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            textForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_nav_dashboard)
            }

            imageToggle.setOnClickListener {
                if (imageToggle.text.toString() == ".") {
                    textRetine.setText(R.string.retine)
                } else {
                    textRetine.setText(R.string.retinut)
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userId = "1"
                    val user = apiService.getUserById(userId)
                    Log.d("User", "FirstName: ${user.firstName}, LastName: ${user.lastName}")
                } catch (e: Exception) {
                    Log.e("Error", "Failed to fetch user: ${e.message}")
                }
            }
        }

    }
}


