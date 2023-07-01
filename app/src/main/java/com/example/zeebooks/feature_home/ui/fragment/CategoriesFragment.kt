package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCategoriesBinding
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import com.example.zeebooks.feature_home.ui.adapter.GridCategoriesAdapter
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    override val resId = com.example.zeebooks.R.layout.fragment_categories
    private lateinit var categoriesAdapter: GridCategoriesAdapter

    private var selectedCategoryId: String? = null

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(com.example.zeebooks.R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {
        sharedViewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                val categories = result.getOrNull()
                categories?.let {
                    showCategories(it)
                }
            } else {
                result.exceptionOrNull()
            }
        })

        sharedViewModel.categories.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                val books = result.getOrNull()
                books?.let {
                }
            } else {
                result.exceptionOrNull()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.titleText.setText(com.example.zeebooks.R.string.categories)
            toolbar.iconBack.visibility = View.INVISIBLE
        }

        categoriesAdapter = GridCategoriesAdapter()

        categoriesAdapter.setOnClick { category ->
            selectedCategoryId = category.id
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToListBooksFragment(categoryId = category.id)
            findNavController().navigate(action)
        }

        binding.categoriesGrid.apply {
            adapter = categoriesAdapter
        }
        observe()
        sharedViewModel.getAllCategories()
    }

    private fun showCategories(categories: List<CategoryModel>) {
        categoriesAdapter.submitList(categories)
    }
}
