package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentExploreBinding


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val resId = R.layout.fragment_explore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonFilter.setOnClickListener {
//            findNavController().navigate(R.id.action_exploreFragment_to_detailsBookFragment)
                findNavController().navigate(R.id.action_exploreFragment_to_filterFragment)
            }
            viewMore.setOnClickListener {
                findNavController().navigate(R.id.action_exploreFragment_to_nav_profile)
            }

            viewMore2.setOnClickListener {
                findNavController().navigate(R.id.action_exploreFragment_to_categoriesFragment)
            }
        }

    }

}

