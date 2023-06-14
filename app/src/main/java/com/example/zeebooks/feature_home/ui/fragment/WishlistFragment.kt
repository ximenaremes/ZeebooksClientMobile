package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentWishlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>() {

    override val resId = R.layout.fragment_wishlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.titleText.setText(R.string.book_favorite_title)
            toolbar.iconBack.visibility =View.INVISIBLE
            toolbar.iconEdit.visibility =View.INVISIBLE

        }


    }


}