package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentAdminDashboardBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminDashboardFragment() : BaseFragment<FragmentAdminDashboardBinding>() {

    override val resId = R.layout.fragment_admin_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.bottomSheet).apply {

            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        with(binding){
            buttonAllUsers.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_usersFragment)
            }
        }


    }

}