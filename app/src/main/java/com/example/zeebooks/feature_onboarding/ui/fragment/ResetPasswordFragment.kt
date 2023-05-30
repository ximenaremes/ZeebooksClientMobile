package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R

import com.example.zeebooks.databinding.FragmentResetPasswordBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {

    override val resId = R.layout.fragment_reset_password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.titleText.setText(R.string.reset_password)

            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnSolicita.setOnClickListener {
                findNavController().navigate(R.id.action_resetPasswordFragment_to_newPasswordFragment)
            }


        }
    }
}