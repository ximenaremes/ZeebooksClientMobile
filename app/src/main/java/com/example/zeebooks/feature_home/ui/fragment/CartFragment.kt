package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCartBinding
import com.example.zeebooks.feature_home.ui.adapter.BookAdapter
import com.example.zeebooks.feature_home.ui.adapter.CartAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {

    override val resId = R.layout.fragment_cart

    private val bookCart = CartAdapter()

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {
        sharedViewModel.getAllCart.observe(viewLifecycleOwner) { books ->
            if (books.isSuccess) {
                val users = books.getOrNull()
                users?.let {
                    bookCart.submitList(it)
                }
            } else {
                books.exceptionOrNull()
            }
        }
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.titleText.setText(R.string.payment_title)
            toolbar.iconBack.visibility =View.INVISIBLE
            toolbar.iconEdit.visibility =View.INVISIBLE

            binding.listCart.adapter = bookCart
            observe()
            val userId = "11"
            sharedViewModel.getBookToCartByUserId(userId = userId)

            if (listCart.isEmpty()){
                buttonCommand.visibility =View.VISIBLE
            }else buttonCommand.visibility =View.GONE
            listCart.adapter?.notifyDataSetChanged()

            buttonCommand.setOnClickListener {
                sharedViewModel.addOrder(userId)
                sharedViewModel.removeAllCart(userId)
                findNavController().navigate(R.id.action_cartFragment_to_orderFragment)
            }

        }





    }


}