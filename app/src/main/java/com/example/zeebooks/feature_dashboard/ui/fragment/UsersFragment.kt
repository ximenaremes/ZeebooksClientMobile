package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUsersBinding
import com.example.zeebooks.feature_dashboard.viewmodel.UsersViewModel
import com.example.zeebooks.feature_profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    override val resId = R.layout.fragment_users

    private val sharedViewModel: UsersViewModel by navGraphViewModels(R.id.nav_dashboard) {
        defaultViewModelProviderFactory
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getAllUsers()


    }
}