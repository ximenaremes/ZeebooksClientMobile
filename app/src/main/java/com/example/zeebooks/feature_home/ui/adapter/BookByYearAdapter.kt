package com.example.zeebooks.feature_home.ui.adapter

import com.example.zeebooks.databinding.ViewBookItemBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Base64
import com.example.zeebooks.databinding.ViewCategoryItemGridviewBinding
import com.example.zeebooks.feature_home.domain.model.CategoryModel


class BookByYearAdapter :
    ListAdapter<BookModel, BookByYearAdapter.BookViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: (BookModel) -> Unit = {}
//    private var deleteClickListener: ((String) -> Unit)? = null
//    private var editClickListener: ((String) -> Unit)? = null
//    private var onClick: (CategoryModel) -> Unit = {}


    inner class BookViewHolder(val binding: ViewBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
    ): BookByYearAdapter.BookViewHolder {
        val binding =
            ViewBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookByYearAdapter.BookViewHolder, position: Int) {
                val book = getItem(position)
                holder.bind(book)

                holder.itemView.setOnClickListener {
                    itemSelectedListener(book)
                }

//                }
//                cardView.setOnClickListener {
//                    selectItem(position)
//                    itemSelectedListener.invoke(getItem(selectedItem))
//                }

//            }
//
//        }
    }

//    fun setOnEditClickListener(listener: (String) -> Unit) {
//        editClickListener = listener
//    }
//
//
//    fun setOnDeleteClickListener(listener: (String) -> Unit) {
//        deleteClickListener = listener
//    }

    fun setOnItemSelectedListener(listener: (BookModel) -> Unit) {
        itemSelectedListener = listener
    }

//    private fun selectItem(index: Int) {
//        if (selectedItem != POSITION_NONE) notifyItemChanged(selectedItem)
//        selectedItem = index
//        notifyItemChanged(selectedItem)
//    }

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

