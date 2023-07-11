package com.example.zeebooks.feature_home.ui.adapter

import android.content.res.ColorStateList
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zeebooks.R
import com.example.zeebooks.databinding.ViewBookItemListBinding
import com.example.zeebooks.feature_home.domain.model.BookModel

class BookAdapterFavourite :
    ListAdapter<BookModel, BookAdapterFavourite.BookViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((BookModel) -> Unit)? = null
    private var itemCommandListener: ((BookModel) -> Unit)? = null
    private var itemFavoriteListener: ((BookModel) -> Unit)? = null
    private var favoriteStates = mutableMapOf<Int, Boolean>()
    private var deleteClickListener: ((String) -> Unit)? = null

    inner class BookViewHolder(val binding: ViewBookItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconFavourite = binding.iconFavourite
        val ratingBar = binding.ratingBar
        val iconCommand = binding.iconCommand

        fun bind(book: BookModel) {
            binding.book = book
            binding.executePendingBindings()

            val baza64Imagine = book.image

            if (baza64Imagine != null) {
                val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                Glide.with(binding.cover.context).load(bytesImagine).into(binding.cover)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BookAdapterFavourite.BookViewHolder {
        val binding =
            ViewBookItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookAdapterFavourite.BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
        getItem(position)?.let { item ->
            with(holder.binding) {

                val rating = item.ratingb?.toFloatOrNull() ?: 0f
                holder.ratingBar.rating = rating
                holder.binding.ratingBar.setIsIndicator(true)

                val color = ContextCompat.getColor(holder.itemView.context, R.color.lighter_yellow)
                holder.binding.ratingBar.progressTintList = ColorStateList.valueOf(color)

                if (favoriteStates.containsKey(position) && favoriteStates[position] == true) {
                    holder.binding.iconFavourite.setImageResource(R.drawable.ic_heart)
                } else {
                    holder.binding.iconFavourite.setImageResource(R.drawable.ic_heart_fulll)
                }

                holder.binding.iconCommand.setOnClickListener {
                    val bookModel = getItem(position)
                    bookModel?.let {
                        itemCommandListener?.invoke(it)
                    }
                }

                holder.binding.iconFavourite.setOnClickListener {
                    val bookId = getItem(position)?.id
                    if (bookId != null) {
                        deleteClickListener?.invoke(bookId)
                    }
                }

                cover.setOnClickListener {
                    selectItem(position)
                    itemSelectedListener?.invoke(getItem(selectedItem))
                }
            }
        }
    }

    private fun toggleFavoriteState(position: Int) {
        val currentState = favoriteStates[position] ?: false
        favoriteStates[position] = !currentState
        notifyItemChanged(position)
    }

    fun setOnDeleteClickListener(listener: (String) -> Unit) {
        deleteClickListener = listener
    }


    fun setOnItemFavoriteListener(listener: (BookModel) -> Unit) {
        itemFavoriteListener = listener
    }

    fun setOnItemCommandListener(listener: (BookModel) -> Unit) {
        itemCommandListener = listener
    }

    fun setOnItemSelectedListener(listener: (BookModel) -> Unit) {
        itemSelectedListener = listener
    }

    private fun selectItem(index: Int) {
        if (selectedItem != POSITION_NONE) notifyItemChanged(selectedItem)
        selectedItem = index
        notifyItemChanged(selectedItem)
    }

    private object BookDiff : DiffUtil.ItemCallback<BookModel>() {
        override fun areContentsTheSame(
            oldItem: BookModel,
            newItem: BookModel
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: BookModel,
            newItem: BookModel
        ): Boolean =
            oldItem == newItem
    }

    companion object {
        const val POSITION_NONE = -1
    }


}

