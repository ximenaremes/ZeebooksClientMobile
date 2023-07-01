package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCategoriesAdminBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.CategoriesListAdapter
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesAdminFragment : BaseFragment<FragmentCategoriesAdminBinding>() {

    override val resId = R.layout.fragment_categories_admin

    private val categoriesListAdapter = CategoriesListAdapter()

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.categoriesLiveData.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val users = result.getOrNull()
                users?.let {
                    categoriesListAdapter.submitList(it)
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

            addCategories.setOnClickListener { findNavController().navigate(R.id.action_categoriesAdminFragment_to_addCategoriesFragment) }

        }

        binding.allCategories.adapter = categoriesListAdapter
        observe()

        sharedViewModel.getAllCategories()

        categoriesListAdapter.setOnDeleteClickListener { categoryId ->
            sharedViewModel.deleteCategoryById(categoryId)
        }

        categoriesListAdapter.setOnEditClickListener {
            findNavController().navigate(R.id.action_categoriesAdminFragment_to_editCategoriesDetailsFragment)
//                        categoryId ->
//            sharedViewModel.deleteCategoryById(categoryId)
        }


    }
}