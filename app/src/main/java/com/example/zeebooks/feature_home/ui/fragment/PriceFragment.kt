package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentPriceBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceFragment : BaseFragment<FragmentPriceBinding>() {

    override val resId = R.layout.fragment_price

    private val bookByPriceAdapter = BookAdapter()
    private var selectedBookId: String? = null

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.booksPrice.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookByPriceAdapter.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val price = arguments?.let { PriceFragmentArgs.fromBundle(it).price }

        binding.allBookByPrice.adapter = bookByPriceAdapter
        observe()
        if (price != null) {
            sharedViewModel.getBooksByPrice(price)
        }

        with(binding) {
            toolbar.titleText.setText(R.string.allBooks)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }

        bookByPriceAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
                PriceFragmentDirections.actionPriceFragmentToDetailsBookFragment( bookId = book.id)
            findNavController().navigate(action)
        }

        bookByPriceAdapter.setOnItemCommandListener { bookModel ->
            val userId = "11"
            sharedViewModel.addToCart(bookModel.id, userId)
            sharedViewModel.addBookToCart(userId, bookModel.id)
        }

    }

}

