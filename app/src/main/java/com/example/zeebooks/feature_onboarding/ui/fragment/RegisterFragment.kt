package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentRegisterBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val resId = R.layout.fragment_register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            existAccount.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            loginText.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_nav_dashboard)
            }
        }
    }
}


