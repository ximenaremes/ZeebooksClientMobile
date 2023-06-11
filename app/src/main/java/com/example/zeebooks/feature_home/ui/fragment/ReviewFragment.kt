package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentReviewBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {

    override val resId = R.layout.fragment_review

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}