package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentChangeUserDetailsBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment


class ChangeUserDetailsFragment : BaseFragment<FragmentChangeUserDetailsBinding>() {

    override val resId = R.layout.fragment_change_user_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.edit_details_text)
        }

    }

}