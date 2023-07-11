package com.example.zeebooks.feature_home.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentExploreBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.ui.adapter.BookByYearAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val resId = R.layout.fragment_explore

    private var selectedBookId: String? = null

    private val bookByYearAdapter = BookByYearAdapter()

    private val bookByRatingAdapter = BookAdapter()

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

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allBookByYear.adapter = bookByYearAdapter
        binding.allBookByRating.adapter = bookByRatingAdapter
        observe()
        sharedViewModel.getAllBooksByYear("2023")
        sharedViewModel.getBooksByRating("5")

        with(binding) {
            buttonFilter.setOnClickListener {
                findNavController().navigate(R.id.action_exploreFragment_to_filterFragment)
            }
            viewMore.setOnClickListener {
                findNavController().navigate(R.id.action_exploreFragment_to_viewMoreBookDFragment)
            }

            viewMore3.setOnClickListener {
                findNavController().navigate(R.id.action_exploreFragment_to_viewMoreBookRFragment)
            }
//            search.setOnClickListener {
//                findNavController().navigate(R.id.action_exploreFragment_to_searchFragment)
//            }
        }

        bookByYearAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
                ExploreFragmentDirections.actionExploreFragmentToDetailsBookFragment(bookId = book.id)
            findNavController().navigate(action)

        }
        bookByRatingAdapter.setOnItemSelectedListener { book ->
            selectedBookId = book.id
            val action =
                ExploreFragmentDirections.actionExploreFragmentToDetailsBookFragment(bookId = book.id)
            findNavController().navigate(action)
        }

//        bookByRatingAdapter.setOnItemFavoriteListener { bookModel ->
//            val userId = "2"
//            val bookId = bookModel.id
//            sharedViewModel.addFavourite(userId, bookId)
//            bookByRatingAdapter.notifyItemChanged(bookByRatingAdapter.currentList.indexOf(bookModel))
//        }
//        bookByRatingAdapter.setOnDeleteClickListener { bookId ->
//            val userId = "2"
//            sharedViewModel.removeFavourite(userId, bookId)
//        }

//        bookByRatingAdapter.setOnItemCommandListener { bookModel ->
//        }

        bookByRatingAdapter.setOnItemFavoriteListener { bookModel ->
            val userId = "11"
            val bookId = bookModel.id
//            sharedViewModel.removeFavourite(userId, bookId)
                sharedViewModel.addFavourite(userId, bookId)

        }
//        sharedViewModel.getBooksByName("")

//        val searchView = binding.search
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                performSearch(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                performSearch(newText)
//                return true
//            }
//        })



    }

//    private fun performSearch(query: String) {
//        sharedViewModel.getBooksByName(query)
//    }


}

