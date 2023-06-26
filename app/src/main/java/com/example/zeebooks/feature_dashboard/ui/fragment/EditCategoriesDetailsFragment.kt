package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentEditCategoriesDetailsBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.CategoriesListAdapter
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditCategoriesDetailsFragment : BaseFragment<FragmentEditCategoriesDetailsBinding>() {

    override val resId = R.layout.fragment_edit_categories_details

    private val categoriesListAdapter = CategoriesListAdapter()

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_dashboard) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }

        }

//        categoriesListAdapter.setOnDeleteClickListener { categoryId ->
////            sharedViewModel.deleteCategoryById(categoryId)
//        apelarea functiei pentru editare
//        }

    }
}