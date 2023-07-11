package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCommandsBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.OrderAdapter
import com.example.zeebooks.feature_dashboard.ui.adapter.UsersListAdapter
import com.example.zeebooks.feature_dashboard.viewmodel.CommandsViewModel
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommandsFragment : BaseFragment<FragmentCommandsBinding>() {

    override val resId = R.layout.fragment_commands

    private val orderList = OrderAdapter()

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }
    private fun observe() {

        sharedViewModel.getAllOrder.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val users = result.getOrNull()
                users?.let {
                    orderList.submitList(it)
                }
            } else {
                result.exceptionOrNull()
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.allCommand)
        }

        binding.orderListR.adapter = orderList
        observe()
        sharedViewModel.getAllOrder()


    }
}