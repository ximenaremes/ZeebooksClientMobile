package com.example.zeebooks.feature_home.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentBookDetailsBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    override val resId = R.layout.fragment_book_details

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_home) {
        defaultViewModelProviderFactory
    }

    private var isRatingSet = false
    private fun observe() {
        sharedViewModel.bookDetails.observe(viewLifecycleOwner) { bookDetails ->
            if (bookDetails.isSuccess) {
                val book = bookDetails.getOrNull()
                book?.let {

                    val baza64Imagine = book.image

                    if (baza64Imagine != null) {
                        val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                        Glide.with(binding.cover.context).load(bytesImagine).into(binding.cover)
                    }

                    binding.authorBook.text = book.name
                    binding.titleBook.text = book.nameAuthor
                    binding.VotesValueText.text = book.ratingb
                    binding.numberPageValueText.text = book.numberOfPage
//                    binding.languageValueText.text = book.
                    binding.yearValueText.text = book.yearOfAppearance
                    binding.textView.text = book.description
                    binding.isbnValue.text = book.isbn
                    binding.statusValue.text=book.status
                    binding.priceValue.text = book.price

                }
            } else {
                bookDetails.exceptionOrNull()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.let { BookDetailsFragmentArgs.fromBundle(it).bookId }
        binding.buttonRating.isEnabled = false


        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            isRatingSet = rating > 0
            updateButtonState()
        }

        binding.toolbar.iconBack.setOnClickListener {
            findNavController().navigateUp()
        }
        observe()
        bookId?.let {
            sharedViewModel.getBooksById(bookId)
        }

        val originalIconSelectorTranslationY = binding.iconSelector.translationY

        val expandedHeight = 770
        val collapsedHeight = 210

        binding.iconSelector.translationY = originalIconSelectorTranslationY

        binding.iconSelector.setOnClickListener {
            val isExpanded = binding.backgroundDescription.layoutParams.height == expandedHeight

            if (isExpanded) {
                val anim = ValueAnimator.ofInt(expandedHeight, collapsedHeight)
                anim.addUpdateListener { valueAnimator ->
                    val layoutParams = binding.backgroundDescription.layoutParams
                    layoutParams.height = valueAnimator.animatedValue as Int
                    binding.backgroundDescription.layoutParams = layoutParams

                    binding.textView.visibility = View.GONE
                    binding.spText.visibility = View.GONE
                    binding.isbnValue.visibility =View.GONE
                    binding.isbnText.visibility =View.GONE
                    binding.statusText.visibility=View.GONE
                    binding.statusValue.visibility=View.GONE
                    binding.priceText.visibility=View.GONE
                    binding.priceValue.visibility=View.GONE
                    binding.RON.visibility=View.GONE


                }
                anim.duration = 300
                anim.start()
            } else {

                val anim = ValueAnimator.ofInt(collapsedHeight, expandedHeight)
                anim.addUpdateListener { valueAnimator ->
                    val layoutParams = binding.backgroundDescription.layoutParams
                    layoutParams.height = valueAnimator.animatedValue as Int
                    binding.backgroundDescription.layoutParams = layoutParams

                    binding.textView.visibility = View.VISIBLE
                      binding.spText.visibility = View.VISIBLE
                    binding.isbnValue.visibility =View.VISIBLE
                    binding.isbnText.visibility =View.VISIBLE
                    binding.statusText.visibility=View.VISIBLE
                    binding.statusValue.visibility=View.VISIBLE
                    binding.priceText.visibility=View.VISIBLE
                    binding.priceValue.visibility=View.VISIBLE
                    binding.RON.visibility=View.VISIBLE
                }
                anim.duration = 300
                anim.start()
            }
        }


        //RATING
        val originalIconSelectorRatingTranslationY = binding.iconSelectorRating.translationY

        val expandedHeightRating = 750
        val collapsedHeightRating = 210

        binding.iconSelectorRating.translationY = originalIconSelectorRatingTranslationY

        binding.iconSelectorRating.setOnClickListener {
            val isExpanded = binding.backgroundRating.layoutParams.height == expandedHeightRating

            if (isExpanded) {
                val anim = ValueAnimator.ofInt(expandedHeightRating, collapsedHeightRating)
                anim.addUpdateListener { valueAnimator ->
                    val layoutParams = binding.backgroundRating.layoutParams
                    layoutParams.height = valueAnimator.animatedValue as Int
                    binding.backgroundRating.layoutParams = layoutParams

                    binding.verticalLine.visibility=View.GONE
                    binding.ratingBar.visibility=View.GONE
                    binding.ratingtext.visibility=View.GONE
                    binding.buttonRating.visibility=View.GONE

                }
                anim.duration = 300
                anim.start()
            } else {

                val anim = ValueAnimator.ofInt(collapsedHeightRating, expandedHeightRating)
                anim.addUpdateListener { valueAnimator ->
                    val layoutParams = binding.backgroundRating.layoutParams
                    layoutParams.height = valueAnimator.animatedValue as Int
                    binding.backgroundRating.layoutParams = layoutParams

                    binding.verticalLine.visibility=View.VISIBLE
                    binding.ratingBar.visibility=View.VISIBLE
                    binding.ratingtext.visibility=View.VISIBLE
                    binding.buttonRating.visibility=View.VISIBLE

                }
                anim.duration = 300
                anim.start()
            }
        }
        binding.buttonRating.setOnClickListener {
            val getRatingValue = binding.ratingBar.rating
            isRatingSet = true
            Toast.makeText(this.context, "Mulțumim pentru rating-ul de $getRatingValue", Toast.LENGTH_SHORT).show()
            binding.buttonRating.isEnabled = false
            binding.ratingBar.isEnabled = false
        }
    }
    private fun updateButtonState() {
        binding.buttonRating.isEnabled = isRatingSet
    }


}