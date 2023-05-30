package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentProfileBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>() {
    override val resId = R.layout.fragment_user_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            toolbar.iconEdit.visibility=View.VISIBLE
            toolbar.titleText.setText(R.string.details_text)
            toolbar.iconEdit.setOnClickListener {
                findNavController().navigate(R.id.action_userDetailsFragment_to_changeUserDetailsFragment)
            }
        }

    }




}