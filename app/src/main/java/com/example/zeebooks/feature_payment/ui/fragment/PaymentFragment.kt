package com.example.zeebooks.feature_payment.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {

    override val resId = R.layout.fragment_payment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.titleText.setText(R.string.payment_title)
            toolbar.iconBack.visibility =View.INVISIBLE
            toolbar.iconEdit.visibility =View.INVISIBLE
            buttonEmptyList.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_categoriesFragment)
            }
            buttonEmptyList.alpha=0.8f
        }


    }


}