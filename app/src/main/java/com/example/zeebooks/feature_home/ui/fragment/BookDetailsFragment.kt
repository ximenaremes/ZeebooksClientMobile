package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBookDetailsBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    override val resId = R.layout.fragment_book_details

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.let { BookDetailsFragmentArgs.fromBundle(it).bookId }

        binding.toolbar.iconBack.setOnClickListener {
            findNavController().navigateUp()
        }

        bookId?.let {
            sharedViewModel.getBooksById(bookId) {book ->
                binding.book = book as BookModel
                binding.name= book.name
                binding.executePendingBindings()
            }
        }
        ///TODO

        binding.buttonRating.setOnClickListener {
            val getRatingValue = binding.ratingBar.rating
            Toast.makeText(this.context, "Rating$getRatingValue", Toast.LENGTH_SHORT).show()
        }
    }
}