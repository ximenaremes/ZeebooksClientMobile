package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentViewMoreBookDBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewMoreBookDFragment : BaseFragment<FragmentViewMoreBookDBinding>() {

    override val resId = R.layout.fragment_view_more_book_d

    private val bookByYearAdapter = BookAdapter()

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookByYearAdapter.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allBookByYear.adapter = bookByYearAdapter
        observe()
        sharedViewModel.getAllBooksByYear("2023")

        with(binding) {
            toolbar.titleText.setText(R.string.list_books_title)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }
        bookByYearAdapter.setOnItemSelectedListener {
            findNavController().navigate(R.id.action_viewMoreBookDFragment_to_detailsBookFragment)
        }

    }

}

