package com.example.zeebooks.feature_dashboard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.databinding.ViewBookItemAdminBinding
import com.example.zeebooks.databinding.ViewOrderItemBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.OrderBookModel

class OrderAdapter :
    ListAdapter<OrderBookModel, OrderAdapter.OrderViewHolder>(BookDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((BookModel?) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null
    private var editClickListener: ((String) -> Unit)? = null


    inner class OrderViewHolder(val viewBinding: ViewOrderItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(order: OrderBookModel) {
            viewBinding.order = order
            viewBinding.executePendingBindings()

            viewBinding.priceValue.text = order.totalPrice.toString()

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderAdapter.OrderViewHolder {
        val binding =
            ViewOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }


    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
        getItem(position)?.let { item ->
            with(holder.viewBinding) {

//                val rating = item.ratingb?.toFloatOrNull() ?: 0f
//                holder.viewBinding.ratingBar.rating = rating
//                holder.viewBinding.ratingBar.setIsIndicator(true)
//
//                val color = ContextCompat.getColor(holder.itemView.context, R.color.lighter_yellow)
//                holder.viewBinding.ratingBar.progressTintList = ColorStateList.valueOf(color)

//                holder.viewBinding.iconDelete.setOnClickListener {
//                    val bookId = getItem(position)?.id
//                    if (bookId != null) {
//                        deleteClickListener?.invoke(bookId)
//                    }
//                }
//
//                holder.viewBinding.iconEditDetails.setOnClickListener {
//                    val bookId = getItem(position)?.id
//                    if (bookId != null) {
//                        editClickListener?.invoke(bookId)
//                    }
//                }
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

    private object BookDiff : DiffUtil.ItemCallback<OrderBookModel>() {
        override fun areContentsTheSame(
            oldItem: OrderBookModel,
            newItem: OrderBookModel
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: OrderBookModel,
            newItem: OrderBookModel
        ): Boolean =
            oldItem == newItem
    }

    companion object {
        const val POSITION_NONE = -1
    }


}


