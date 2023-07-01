package com.example.zeebooks.feature_dashboard.ui.fragment


import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentUsersBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.UsersListAdapter
import com.example.zeebooks.feature_dashboard.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    override val resId = R.layout.fragment_users

    private val usersListAdapter = UsersListAdapter()

    private val sharedViewModel: UsersViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.usersLiveData.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val users = result.getOrNull()
                users?.let {
                    usersListAdapter.submitList(it)
                }
            } else {
                result.exceptionOrNull()
            }
        }
        sharedViewModel.numberOfUsers.observe(viewLifecycleOwner) { number ->
            binding.totalNumber.text = number.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }

            buttonAddUser.setOnClickListener { findNavController().navigate(R.id.action_usersFragment_to_editUserDetailsFragment) }
        }

        binding.allUsers.adapter = usersListAdapter
        sharedViewModel.getNumberOfUsers()
        observe()

        usersListAdapter.setOnDeleteClickListener { userId ->
            showDialog(userId)

        }

    }

    private fun showDialog(userId: String) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.view_custom_popup_logout, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        val title = popupView.findViewById<TextView>(R.id.logoutText)
        val message = popupView.findViewById<TextView>(R.id.messageText)
        title.setText(R.string.delete_user)
        message.setText(R.string.delete_message)

        val btnCancel = popupView.findViewById<TextView>(R.id.btn_cancel)
        val btnConfirm = popupView.findViewById<TextView>(R.id.btn_confirm)

        btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }
        btnConfirm.setOnClickListener {
            sharedViewModel.deleteUserById(userId)
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(requireView(), Gravity.CENTER, 0, 230)

    }


}