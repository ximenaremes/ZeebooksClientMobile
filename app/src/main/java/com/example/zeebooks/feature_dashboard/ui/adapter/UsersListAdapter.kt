package com.example.zeebooks.feature_dashboard.ui.adapter

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.databinding.ViewUserItemBinding
import java.text.SimpleDateFormat
import java.util.*


class UsersListAdapter :
    ListAdapter<User, UsersListAdapter.UserViewHolder>(UserDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((User?) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null

    inner class UserViewHolder(val viewBinding: ViewUserItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        val cardView = viewBinding.cardView
        val iconSelector = viewBinding.iconSelector
        val originalIconSelectorTranslationY = iconSelector.translationY
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsersListAdapter.UserViewHolder {
        val binding =
            ViewUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UsersListAdapter.UserViewHolder, position: Int) {
        getItem(position)?.let { item ->
            with(holder.viewBinding) {
                if (item.role == "STANDARD") {

                    lastName = item.lastName
                    firstName = item.firstName
                    email = item.email
                    numberOfPhone = item.numberPhone

                    val inputDate = item.dateOfJoin

                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH)
                    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale("ro"))

                    val date = inputFormat.parse(inputDate)
                    dateOfJoin = outputFormat.format(date)

                    cardView.visibility = View.VISIBLE

                } else {
                    cardView.visibility = View.GONE
                }

                holder.viewBinding.buttonDelete.setOnClickListener {
                    val userId = getItem(position)?.id
                    if (userId != null) {
                        deleteClickListener?.invoke(userId)
                    }
                }

                val expandedHeight = 650
                val collapsedHeight = 270

                holder.iconSelector.translationY = holder.originalIconSelectorTranslationY

                holder.iconSelector.setOnClickListener {
                    val isExpanded = holder.cardView.layoutParams.height == expandedHeight

                    if (isExpanded) {
                        val anim = ValueAnimator.ofInt(expandedHeight, collapsedHeight)
                        anim.addUpdateListener { valueAnimator ->
                            val layoutParams = holder.cardView.layoutParams
                            layoutParams.height = valueAnimator.animatedValue as Int
                            holder.cardView.layoutParams = layoutParams

                            emailText.visibility = View.GONE
                            emailValue.visibility = View.GONE
                            numberPhoneText.visibility = View.GONE
                            numberPhoneValue.visibility = View.GONE
                            dateOfJoinText.visibility = View.GONE
                            dateOfJoinValue.visibility = View.GONE
                            buttonDelete.visibility = View.GONE
                        }
                        anim.duration = 300
                        anim.start()
                    } else {

                        val anim = ValueAnimator.ofInt(collapsedHeight, expandedHeight)
                        anim.addUpdateListener { valueAnimator ->
                            val layoutParams = holder.cardView.layoutParams
                            layoutParams.height = valueAnimator.animatedValue as Int
                            holder.cardView.layoutParams = layoutParams

                            emailText.visibility = View.VISIBLE
                            emailValue.visibility = View.VISIBLE
                            numberPhoneText.visibility = View.VISIBLE
                            numberPhoneValue.visibility = View.VISIBLE
                            dateOfJoinText.visibility = View.VISIBLE
                            dateOfJoinValue.visibility = View.VISIBLE
                            buttonDelete.visibility = View.VISIBLE
                        }
                        anim.duration = 300
                        anim.start()
                    }
                }

                cardView.setOnClickListener {
                    selectItem(position)
                    itemSelectedListener?.invoke(getItem(selectedItem))
                }
            }
        }
    }

    fun setOnDeleteClickListener(listener: (String) -> Unit) {
        deleteClickListener = listener
    }

    fun setOnItemSelectedListener(listener: (User?) -> Unit) {
        itemSelectedListener = listener
    }

    private fun selectItem(index: Int) {
        if (selectedItem != POSITION_NONE) notifyItemChanged(selectedItem)
        selectedItem = index
        notifyItemChanged(selectedItem)
    }

    private object UserDiff : DiffUtil.ItemCallback<User>() {
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    companion object {
        const val POSITION_NONE = -1
    }


}

