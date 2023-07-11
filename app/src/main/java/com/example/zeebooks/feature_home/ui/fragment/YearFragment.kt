package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentYearBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YearFragment : BaseFragment<FragmentYearBinding>() {

    override val resId = R.layout.fragment_year

    private val bookByYearAdapter = BookAdapter()
    private var selectedBookId: String? = null

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

        val year = arguments?.let { YearFragmentArgs.fromBundle(it).year }

        binding.allBookByYear.adapter = bookByYearAdapter
        observe()
        if (year != null) {
            sharedViewModel.getAllBooksByYear(year)
        }


        with(binding) {
            toolbar.titleText.setText(R.string.allBooks)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }

        bookByYearAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
               YearFragmentDirections.actionYearFragmentToDetailsBookFragment( bookId = book.id)
            findNavController().navigate(action)
        }

        bookByYearAdapter.setOnItemCommandListener { bookModel ->
            val userId = "11"
            sharedViewModel.addToCart(bookModel.id, userId)
            sharedViewModel.addBookToCart(userId, bookModel.id)
        }

    }

}

