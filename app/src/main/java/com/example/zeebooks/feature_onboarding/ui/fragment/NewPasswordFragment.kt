package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentNewPasswordBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment

class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {

    override val resId = R.layout.fragment_new_password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            buttonLogin.setOnClickListener {
                findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
            }
//            loginText.setOnClickListener {
//                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
//            }

        }
    }

}