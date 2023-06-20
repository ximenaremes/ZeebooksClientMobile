package com.example.zeebooks.feature_profile.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentProfileBinding
import com.example.zeebooks.feature_onboarding.viewmodel.RegisterViewModel
import com.example.zeebooks.feature_profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val resId = R.layout.fragment_profile

    private val sharedViewModel: ProfileViewModel by navGraphViewModels(R.id.nav_profile) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            personalDetails.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_userDetailsFragment)
            }
            language.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_languageFragment)
            }
            about.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)
            }
            logout.setOnClickListener {
                showDialog()
            }
        }
        changeDarkMode()
    }


    private fun changeDarkMode() {

        val sharedPreference = context?.getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreference?.edit()

        binding.imageToggle.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor?.putBoolean("night", false)
                editor?.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor?.putBoolean("night", true)
                editor?.apply()
            }
        }
        if (sharedPreference?.getBoolean("night", false) == true) {
            binding.imageToggle.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
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
            sharedViewModel.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_nav_onboarding)
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(requireView(), Gravity.CENTER, 0, 195)

    }

}