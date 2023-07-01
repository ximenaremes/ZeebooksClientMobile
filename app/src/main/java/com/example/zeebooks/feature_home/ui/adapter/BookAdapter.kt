package com.example.zeebooks.feature_home.ui.adapter

import com.example.zeebooks.databinding.ViewBookItemListBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Base64

class BookAdapter :
    ListAdapter<BookModel, BookAdapter.BookViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((BookModel) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null
    private var editClickListener: ((String) -> Unit)? = null


    inner class BookViewHolder(val viewBinding: ViewBookItemListBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BookAdapter.BookViewHolder {
        val binding =
            ViewBookItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        getItem(position)?.let { item ->
            with(holder.viewBinding) {

                name = item.name
                nameAuthor =item.nameAuthor
                image = item.image
                ratingb = item.ratingb

                val baza64Imagine = item.image

                if (baza64Imagine != null) {
                    val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                    Glide.with(holder.itemView.context).load(bytesImagine).into(holder.viewBinding.cover)
                }


//                holder.viewBinding.iconDelete.setOnClickListener {
//                    val categoryId = getItem(position)?.id
//                    if (categoryId != null) {
//                        deleteClickListener?.invoke(categoryId)
//                    }
//                }
//
//                holder.viewBinding.iconEditDetails.setOnClickListener {
//                    val categoryId = getItem(position)?.id
//                    if (categoryId != null) {
//                        editClickListener?.invoke(categoryId)
//                    }
//                }
                cardView.setOnClickListener {
                    selectItem(position)
                    itemSelectedListener?.invoke(getItem(selectedItem))
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

