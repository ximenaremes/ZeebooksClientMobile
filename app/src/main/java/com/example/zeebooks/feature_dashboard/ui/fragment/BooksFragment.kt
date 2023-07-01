package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BooksFragment : BaseFragment<FragmentBooksBinding>() {

    override val resId = R.layout.fragment_books

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.allBooks)

            imageAdd.setOnClickListener { findNavController().navigate(R.id.action_booksFragment_to_editBookDetailsFragment) }

        }
    }


}
