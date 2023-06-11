package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentLanguageBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.feature_profile.domain.model.LanguageItem
import com.example.zeebooks.feature_profile.domain.model.LanguageItemType
import com.example.zeebooks.feature_profile.ui.adapter.ListLanguageAdapter
import com.example.zeebooks.feature_profile.viewmodel.LanguageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {

    override val resId = R.layout.fragment_language
    private var languageAdapter = ListLanguageAdapter()

    private val sharedViewModel: LanguageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            toolbar.titleText.setText(R.string.language_text)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }
        setupReconciliationReasonAdapter()
    }

    private fun setupReconciliationReasonAdapter() = with(binding) {
        val list: MutableList<LanguageItem> = mutableListOf()
        list.add(
            LanguageItem(
                LanguageItemType.ROMANA.id,
                getString(R.string.language_romana),
                sharedViewModel.checkIfInvoiceClosingReasonIsSelected(LanguageItemType.ROMANA.id)
            )
        )
        list.add(
            LanguageItem(
                LanguageItemType.ENGLEZA.id,
                getString(R.string.language_engleza),
                sharedViewModel.checkIfInvoiceClosingReasonIsSelected(LanguageItemType.ENGLEZA.id)
            )
        )
        list.add(
            LanguageItem(
                LanguageItemType.FRANCEZA.id,
                getString(R.string.language_franceza),
                        sharedViewModel.checkIfInvoiceClosingReasonIsSelected(LanguageItemType.FRANCEZA.id)
            )
        )
        languageAdapter.submitList(list)
        languageAdapter.setOnItemSelectedListener {
            it?.let { reconciliationReasonItem ->
                sharedViewModel.setInvoiceClosingReason(reconciliationReasonItem)
                findNavController().navigateUp()
            }
        }
            binding.reconciliationReasonList.adapter = languageAdapter
    }
}






