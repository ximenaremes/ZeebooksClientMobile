package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Extras
import com.example.zeebooks.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditionsFragment : BaseFragment<FragmentTermsAndConditionsBinding>(){

    override val resId = R.layout.fragment_terms_and_conditions
    private var isTermsAndConditions: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isTermsAndConditions = arguments?.getBoolean(Extras.IS_TERMS_AND_CONDITIONS) ?: false

        with(binding) {

            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }

            toolbar.titleText.setText(R.string.terms_and_condition_text)

        }

    }




}