package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentFilterBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>() {
    override val resId = R.layout.fragment_filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setImageResource(R.drawable.ic_close)
            toolbar.titleText.setText(R.string.filter_title)
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

}