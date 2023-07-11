package com.example.zeebooks.feature_home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.R
import com.example.zeebooks.databinding.FragmentFilterBinding
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>() {
    override val resId = R.layout.fragment_filter

//    private val getAllAuthorAdapter = FilterAdapter()
//    private val getAllAuthorAdapter = StringAdapter()


    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }
//    private fun observe() {
//
//        sharedViewModel.getAllAuthor.observe(viewLifecycleOwner) { books ->
//            if (books.isSuccess) {
//                val users = books.getOrNull()
//                users?.let {
////                    getAllAuthorAdapter.submitList(it)
//                    getAllAuthorAdapter.submitList(it)
//                }
//            } else {
//                books.exceptionOrNull()
//            }
//        }
//    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.iconBack.setImageResource(R.drawable.ic_close)
            toolbar.titleText.setText(R.string.filter_title)
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }

            //AUTHOR
            textAuthor1.setOnClickListener {
                val author = binding.textMediuValue.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToAuthorFragment(author)
                findNavController().navigate(action)
            }
            textAuthor2.setOnClickListener {
                val author = binding.textAuthor2.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToAuthorFragment(author)
                findNavController().navigate(action)
            }
            textAuthor3.setOnClickListener {
                val author = binding.textAuthor3.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToAuthorFragment(author)
                findNavController().navigate(action)
            }
            textAuthor4.setOnClickListener {
                val author = binding.textAuthor4.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToAuthorFragment(author)
                findNavController().navigate(action)
            }
            textAuthor5.setOnClickListener {
                val author = binding.textAuthor5.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToAuthorFragment(author)
                findNavController().navigate(action)
            }

            //PRICE
            textMic.setOnClickListener {
                val price = binding.textMicValue.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToPriceFragment(price)
                findNavController().navigate(action)
            }
            textMediu.setOnClickListener {
                val price = binding.textMediuValue.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToPriceFragment(price)
                findNavController().navigate(action)
            }

            textMare.setOnClickListener {
                val price = binding.textMareValue.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToPriceFragment(price)
                findNavController().navigate(action)
            }

            //RATING
            textRating5.setOnClickListener {
                val rating = binding.textRating5.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }
            textRating4.setOnClickListener {
                val rating = binding.textRating4.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }
            textRating3.setOnClickListener {
                val rating = binding.textRating3.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }
            textRating2.setOnClickListener {
                val rating = binding.textRating2.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }
            textRating1.setOnClickListener {
                val rating = binding.textRating1.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }
            textRating0.setOnClickListener {
                val rating = binding.textRating0.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToRatingFragment(rating)
                findNavController().navigate(action)
            }

            //YEAR

            textYear2023.setOnClickListener {
                val year = binding.textYear2023.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToYearFragment(year)
                findNavController().navigate(action)
            }
            textYear2022.setOnClickListener {
                val year = binding.textYear2022.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToYearFragment(year)
                findNavController().navigate(action)
            }
            textYear2021.setOnClickListener {
                val year = binding.textYear2021.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToYearFragment(year)
                findNavController().navigate(action)
            }
            textYear2020.setOnClickListener {
                val year = binding.textYear2020.text.toString()
                val action = FilterFragmentDirections.actionFilterFragmentToYearFragment(year)
                findNavController().navigate(action)
            }

        }

    }

}