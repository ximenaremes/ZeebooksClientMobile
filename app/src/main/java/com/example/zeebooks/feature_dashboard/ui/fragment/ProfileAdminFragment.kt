package com.example.zeebooks.feature_dashboard.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBooksBinding
import com.example.zeebooks.databinding.FragmentProfileAdminBinding
import com.example.zeebooks.feature_profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileAdminFragment : BaseFragment<FragmentProfileAdminBinding>() {

    override val resId = R.layout.fragment_profile_admin

//
//    private val sharedViewModel: ProfileViewModel by navGraphViewModels(R.id.nav_profile) {
//        defaultViewModelProviderFactory
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            logout.setOnClickListener {
                showDialog()
            }

        }
    }
    private fun showDialog() {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.view_custom_popup_logout, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        val btnCancel = popupView.findViewById<TextView>(R.id.btn_cancel)
        val btnConfirm = popupView.findViewById<TextView>(R.id.btn_confirm)


        btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }

        btnConfirm.setOnClickListener {
//            sharedViewModel.signOut()
            findNavController().navigate(R.id.action_profileAdminFragment_to_loginFragment)
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(requireView(), Gravity.CENTER, 0, 195)

    }



}