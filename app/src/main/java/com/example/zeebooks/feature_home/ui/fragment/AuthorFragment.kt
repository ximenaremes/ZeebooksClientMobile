package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentAuthorBinding
import com.example.zeebooks.databinding.FragmentPriceBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorFragment : BaseFragment<FragmentAuthorBinding>() {

    override val resId = R.layout.fragment_author

    private val bookByAuthorAdapter = BookAdapter()
    private var selectedBookId: String? = null

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.booksPrice.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookByAuthorAdapter.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val author = arguments?.let { AuthorFragmentArgs.fromBundle(it).author }

        binding.allBookByAuthor.adapter = bookByAuthorAdapter
        observe()
        if (author != null) {
//            sharedViewModel.getBooksByAuthor(author)
            sharedViewModel.getBooksByPrice(author)
        }

        with(binding) {
            toolbar.titleText.setText(R.string.list_books_title)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }

        bookByAuthorAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
                AuthorFragmentDirections.actionAuthorFragmentToDetailsBookFragment( bookId = book.id)
            findNavController().navigate(action)
        }

        bookByAuthorAdapter.setOnItemCommandListener { bookModel ->
            val userId = "11"
            sharedViewModel.addToCart(bookModel.id, userId)
            sharedViewModel.addBookToCart(userId, bookModel.id)
        }

    }

}

