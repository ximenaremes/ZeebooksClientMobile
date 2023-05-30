package com.example.zeebooks.feature_profile.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val resId = R.layout.fragment_profile

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


}

