package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentCategoriesBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.feature_dashboard.domain.model.CategoryModel
import com.example.zeebooks.feature_dashboard.ui.adapter.CategoriesAdapter

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val resId = R.layout.fragment_categories

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.titleText.setText(R.string.categories)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }


        }
    }
}