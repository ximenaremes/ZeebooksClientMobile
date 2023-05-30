package com.example.zeebooks.feature_profile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.R
import com.example.zeebooks.databinding.ViewLanguageItemBinding
import com.example.zeebooks.feature_profile.domain.model.LanguageItem

class ListLanguageAdapter: ListAdapter<LanguageItem, ListLanguageAdapter.LanguageViewHolder>( TransactionDiff ) {

    var NONE_SELECTED = -1
    private lateinit var selectedItem: LanguageItem
    private var itemSelectedListener: ((LanguageItem?) -> Unit)? = null

    inner class LanguageViewHolder(val viewBinding: ViewLanguageItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListLanguageAdapter.LanguageViewHolder {
        val binding =
            ViewLanguageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LanguageViewHolder(binding)
    }

//    override fun onBindViewHolder(
//        holder: ListLanguageAdapter.LanguageViewHolder,
//        position: Int
//    ) {
//        getItem(position)?.let { reconciliationReasonItem ->
//            with(holder.viewBinding) {
//                selectedItem = reconciliationReasonItem
//                var iconVisbility = View.INVISIBLE
//                languageItemText.text = reconciliationReasonItem.name
//
//                var backgroundRes = R.drawable.background_rounded_white
//                if (reconciliationReasonItem.isSelected) {
//                    backgroundRes = R.drawable.background_button_field_border
//                    iconVisbility = View.VISIBLE
//                }
//                contentLayout.background = ContextCompat.getDrawable(
//                    holder.itemView.context,
//                    backgroundRes
//                )
//
//                checkmark.visibility = iconVisbility
//
//                contentLayout.setOnClickListener {
//                    selectItemByPosition(position)
//                    itemSelectedListener?.invoke(getItem(NONE_SELECTED))
//                }
//            }
//        }
//    }

    override fun onBindViewHolder(
        holder: ListLanguageAdapter.LanguageViewHolder,
        position: Int
    ) {
        getItem(position)?.let { languageItem ->
            with(holder.viewBinding) {
                languageItemText.text = languageItem.name
                val backgroundRes = if (languageItem.isSelected) {
                    R.drawable.background_button_field_border
                } else {
                    R.drawable.background_rounded_white
                }
                contentLayout.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    backgroundRes
                )
                contentLayout.setOnClickListener {
                    selectItemByPosition(position)
                    itemSelectedListener?.invoke(getItem(position))
                }
            }
        }
    }

    fun selectItem(languageItem: LanguageItem) {
        val previousSelectedIndex = currentList.indexOfFirst { it.isSelected }
        val newSelectedIndex = currentList.indexOf(languageItem)

        if (previousSelectedIndex != newSelectedIndex) {
            getItem(previousSelectedIndex)?.isSelected = false
            getItem(newSelectedIndex)?.isSelected = true

            notifyItemChanged(previousSelectedIndex)
            notifyItemChanged(newSelectedIndex)
        }
    }

    fun setOnItemSelectedListener(listener: (LanguageItem?) -> Unit) {
        itemSelectedListener = listener
    }

    fun selectItemByPosition(index: Int) {
        if (NONE_SELECTED != -1) notifyItemChanged(NONE_SELECTED)
        NONE_SELECTED = index
        notifyItemChanged(NONE_SELECTED)
    }

    private object TransactionDiff : DiffUtil.ItemCallback<LanguageItem>() {
        override fun areContentsTheSame(
            oldItem: LanguageItem,
            newItem: LanguageItem,
        ) = true

        override fun areItemsTheSame(
            oldItem: LanguageItem,
            newItem: LanguageItem,
        ) = true
    }
}