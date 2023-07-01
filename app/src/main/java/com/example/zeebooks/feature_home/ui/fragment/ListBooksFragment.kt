package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentListBooksBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListBooksFragment : BaseFragment<FragmentListBooksBinding>() {

    override val resId = R.layout.fragment_list_books

    private val bookByCategoryId = BookAdapter()

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.categories.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookByCategoryId.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.titleText.setText(R.string.list_books_title)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }
        val categoryId = arguments?.let { ListBooksFragmentArgs.fromBundle(it).categoryId }

        binding.allBooksByCategory.adapter = bookByCategoryId
        observe()

        categoryId?.let {
            sharedViewModel.getBookByCategoryId(categoryId)
        }


    }

}

