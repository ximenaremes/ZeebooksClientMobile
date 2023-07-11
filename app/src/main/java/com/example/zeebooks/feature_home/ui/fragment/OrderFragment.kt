package com.example.zeebooks.feature_home.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCartBinding
import com.example.zeebooks.databinding.FragmentOrderBinding
import com.example.zeebooks.feature_home.ui.adapter.CartAdapter
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>() {

    override val resId = R.layout.fragment_order

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private fun observe() {
        sharedViewModel.addToCart.observe(viewLifecycleOwner) { order ->
            if (order.isSuccess) {
                val book = order.getOrNull()
                book?.let {
                    binding.plataValue.text = book.totalPrice.toString()
                }
            } else {
                order.exceptionOrNull()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.titleText.setText(R.string.payment_title_order)
            toolbar.iconBack.visibility=View.INVISIBLE
//            toolbar.iconBack.setOnClickListener {
//                findNavController().navigateUp()
//            }

            sharedViewModel.getTotalNumberOrder()
            observe()

            sharedViewModel.number.observe(viewLifecycleOwner) { number ->
                val totalNumber = number.toString()
                sharedViewModel.getAllOrdersById("61")
            }

            buttonCommand.setOnClickListener {
                showSuccess()
            }
        }
    }

    private fun showSuccess() {
        val dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.view_order_placed_successfully)

        Handler().postDelayed({
            dialog?.dismiss()
            findNavController().navigate(R.id.action_orderFragment_to_exploreFragment)

        }, 5500)

        dialog?.show()
    }

}