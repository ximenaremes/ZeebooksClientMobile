package com.example.zeebooks.feature_dashboard.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBooksBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.AllBooksAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BooksFragment : BaseFragment<FragmentBooksBinding>() {

    override val resId = R.layout.fragment_books

    private val booksList = AllBooksAdapter()

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.allBooks.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val users = result.getOrNull()
                users?.let {
                    booksList.submitList(it)
                }
            } else {
                result.exceptionOrNull()
            }
        }
        sharedViewModel.numberBook.observe(viewLifecycleOwner) { number ->
            binding.totalNumber.text = number.toString()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.allBooks)

            imageAdd.setOnClickListener { findNavController().navigate(R.id.action_booksFragment_to_addBookFragment) }

        }
        binding.allBooks.adapter = booksList
        sharedViewModel.getTotalBooks()
        observe()
        sharedViewModel.getAllBooks()

        booksList.setOnDeleteClickListener { bookId ->
            showDialog(bookId)
        }

        booksList.setOnEditClickListener {
            findNavController().navigate(R.id.action_booksFragment_to_editBookDetailsFragment)
        }

    }

    private fun showDialog(bookId: String) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.view_custom_popup_logout, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        val title = popupView.findViewById<TextView>(R.id.logoutText)
        val message = popupView.findViewById<TextView>(R.id.messageText)
        title.setText(R.string.delete_user)
        message.setText(R.string.delete_message_book)

        val btnCancel = popupView.findViewById<TextView>(R.id.btn_cancel)
        val btnConfirm = popupView.findViewById<TextView>(R.id.btn_confirm)

        btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }
        btnConfirm.setOnClickListener {
            sharedViewModel.deleteBookById(bookId)
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(requireView(), Gravity.CENTER, 0, 230)

    }


}
