package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    override val resId = R.layout.fragment_users

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}