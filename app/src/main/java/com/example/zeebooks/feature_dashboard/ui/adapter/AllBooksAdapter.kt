package com.example.zeebooks.feature_dashboard.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Base64
import androidx.core.content.ContextCompat
import com.example.zeebooks.R
import com.example.zeebooks.databinding.ViewBookItemAdminBinding
import com.example.zeebooks.feature_home.domain.model.BookModel



class AllBooksAdapter :
    ListAdapter<BookModel, AllBooksAdapter.BookViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((BookModel?) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null
    private var editClickListener: ((String) -> Unit)? = null


    inner class BookViewHolder(val viewBinding: ViewBookItemAdminBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(book: BookModel) {
            viewBinding.book = book
            viewBinding.executePendingBindings()

            val baza64Imagine = book.image

            if (baza64Imagine != null) {
                val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                Glide.with(viewBinding.cover.context).load(bytesImagine).into(viewBinding.cover)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AllBooksAdapter.BookViewHolder {
        val binding =
            ViewBookItemAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AllBooksAdapter.BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
        getItem(position)?.let { item ->
            with(holder.viewBinding) {

                val rating = item.ratingb?.toFloatOrNull() ?: 0f
                holder.viewBinding.ratingBar.rating = rating
                holder.viewBinding.ratingBar.setIsIndicator(true)

                val color = ContextCompat.getColor(holder.itemView.context, R.color.lighter_yellow)
                holder.viewBinding.ratingBar.progressTintList = ColorStateList.valueOf(color)

                holder.viewBinding.iconDelete.setOnClickListener {
                    val bookId = getItem(position)?.id
                    if (bookId != null) {
                        deleteClickListener?.invoke(bookId)
                    }
                }

                holder.viewBinding.iconEditDetails.setOnClickListener {
                    val bookId = getItem(position)?.id
                    if (bookId != null) {
                        editClickListener?.invoke(bookId)
                    }
                }
            }
        }
    }

    fun setOnEditClickListener(listener: (String) -> Unit) {
        editClickListener = listener
    }


    fun setOnDeleteClickListener(listener: (String) -> Unit) {
        deleteClickListener = listener
    }

    fun setOnItemSelectedListener(listener: (BookModel?) -> Unit) {
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


