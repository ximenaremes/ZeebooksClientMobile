package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentExploreBinding
import com.example.zeebooks.databinding.FragmentViewMoreBookRBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.ui.adapter.BookByYearAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewMoreBookRFragment : BaseFragment<FragmentViewMoreBookRBinding>() {

    override val resId = R.layout.fragment_view_more_book_r

    private val bookByRatingAdapter = BookAdapter()

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

//    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_home) {
//        defaultViewModelProviderFactory
//    }


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

        binding.allBookByRating.adapter = bookByRatingAdapter
        observe()
        sharedViewModel.getBooksByRating("5")

        with(binding) {
            toolbar.titleText.setText(R.string.recomandate)
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
        }
        bookByRatingAdapter.setOnItemSelectedListener {
            findNavController().navigate(R.id.action_viewMoreBookRFragment_to_detailsBookFragment)
        }
    }}