package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentRatingBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingFragment : BaseFragment<FragmentRatingBinding>() {

    override val resId = R.layout.fragment_rating

    private val bookByRatingAdapter = BookAdapter()
    private var selectedBookId: String? = null

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.booksRating.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookByRatingAdapter.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rating = arguments?.let { RatingFragmentArgs.fromBundle(it).rating }

        binding.allBookByRating.adapter = bookByRatingAdapter
        observe()
        if (rating != null) {
            sharedViewModel.getBooksByRating(rating)
        }

        with(binding) {
            toolbar.titleText.setText(R.string.allBooks)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }

        bookByRatingAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
                RatingFragmentDirections.actionRatingFragmentToDetailsBookFragment(   bookId = book.id)

            findNavController().navigate(action)
        }
    }
}