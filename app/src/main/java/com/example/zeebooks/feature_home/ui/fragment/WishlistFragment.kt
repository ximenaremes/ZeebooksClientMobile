package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentWishlistBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapterFavourite
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>() {

    override val resId = R.layout.fragment_wishlist

    private val bookFavourite = BookAdapterFavourite()


    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.booksFavourite.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookFavourite.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.titleText.setText(R.string.book_favorite_title)
            toolbar.iconBack.visibility = View.INVISIBLE
            toolbar.iconEdit.visibility = View.INVISIBLE

        }

        binding.favoriteBooksRecyclerView.adapter = bookFavourite
        observe()
        sharedViewModel.getFavoritesByUserId(userId = "11")

        val favoriteBooksRecyclerView = binding.favoriteBooksRecyclerView
        val emptyLayout = binding.emptyList



        bookFavourite.setOnDeleteClickListener { bookId ->
            val userId = "11"
            sharedViewModel.removeFavourite(userId, bookId)
        }
        if (favoriteBooksRecyclerView.adapter != null) {
            favoriteBooksRecyclerView.visibility = View.VISIBLE
            emptyLayout.visibility = View.GONE
        } else {
            favoriteBooksRecyclerView.visibility = View.GONE
            emptyLayout.visibility = View.VISIBLE
        }

        bookFavourite.setOnItemCommandListener { bookModel ->
            val userId = "11"
            sharedViewModel.addToCart(bookModel.id, userId)
            sharedViewModel.addBookToCart(userId, bookModel.id)
        }


    }




}