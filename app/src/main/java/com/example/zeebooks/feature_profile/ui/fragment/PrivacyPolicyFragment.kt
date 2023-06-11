package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Extras
import com.example.zeebooks.databinding.FragmentPrivacyPolicyBinding
import com.example.zeebooks.databinding.FragmentTermsAndConditionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyFragment : BaseFragment<FragmentPrivacyPolicyBinding>(){

    override val resId = R.layout.fragment_privacy_policy


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            toolbar.titleText.setText(R.string.privacy)
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }

    }




}