package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.BuildConfig
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentAboutUsBinding

class AboutUsFragment : BaseFragment<FragmentAboutUsBinding>() {

    override val resId = R.layout.fragment_about_us

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            toolbar.titleText.setText(R.string.about_us_text)

            textVersion.text = "Version" + " " + BuildConfig.VERSION_NAME

            layoutTermsAndConditions.setOnClickListener {
                findNavController().navigate(R.id.action_aboutUsFragment_to_termsAndConditionsFragment)
            }
            layoutPrivacy.setOnClickListener {
                findNavController().navigate(R.id.action_aboutUsFragment_to_privacyPolicyFragment)
            }
            layoutQuestions.setOnClickListener {
                findNavController().navigate(R.id.action_aboutUsFragment_to_questionsFragment)
            }
        }
    }
}
