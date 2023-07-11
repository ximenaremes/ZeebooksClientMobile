package com.example.zeebooks.feature_home.ui.adapter

import com.example.zeebooks.feature_home.domain.model.BookModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.databinding.ViewItemFilterBinding

class FilterAdapter :
    ListAdapter<BookModel, FilterAdapter.BookViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((BookModel) -> Unit)? = null
    private var itemCommandListener: ((BookModel) -> Unit)? = null
    private var itemFavoriteListener: ((BookModel) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null
    private var favoriteStates = mutableMapOf<Int, Boolean>()

    inner class BookViewHolder(val binding: ViewItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nameAuthor: BookModel) {
//            binding.nameAuthor = nameAuthor
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FilterAdapter.BookViewHolder {
        val binding =
            ViewItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FilterAdapter.BookViewHolder, position: Int) {
        val nameAuthor = getItem(position)
        getItem(position)?.let { item ->
            with(holder.binding) {
                holder.bind(nameAuthor)



//                holder.binding.iconFavourite.setOnClickListener {
//                    val bookModel = getItem(position)
//                    bookModel?.let {
//                        toggleFavoriteState(position)
//                        itemFavoriteListener?.invoke(it)
//                    }
//                }
//                cover.setOnClickListener {
//                    selectItem(position)
//                    itemSelectedListener?.invoke(getItem(selectedItem))
//                }
            }
        }
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

