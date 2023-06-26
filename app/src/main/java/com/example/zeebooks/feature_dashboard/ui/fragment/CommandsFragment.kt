package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCommandsBinding
import com.example.zeebooks.feature_dashboard.viewmodel.CommandsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommandsFragment : BaseFragment<FragmentCommandsBinding>() {

    override val resId = R.layout.fragment_commands

    private val sharedViewModel: CommandsViewModel by navGraphViewModels(R.id.nav_dashboard) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}