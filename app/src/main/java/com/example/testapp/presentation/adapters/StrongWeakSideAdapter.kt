package com.example.testapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.StrongWeakSideLayoutBinding
import com.example.testapp.presentation.models.StrongWeakSideItem

class StrongWeakSideAdapter :
    ListAdapter<StrongWeakSideItem, StrongWeakSideAdapter.ViewHolder>(
        DiffUtilCallbackStrongWeakSideItem
    ) {

    fun setList(data: List<StrongWeakSideItem>) {
        submitList(null)
        submitList(data)
    }

    override fun getItemCount() = currentList.count()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            StrongWeakSideLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: StrongWeakSideLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StrongWeakSideItem) {
            binding.tvTitle.text = item.text
            binding.tvTitle.setTextColor(binding.tvTitle.context.getColor(item.textColor))
            binding.cvStrongWeakSideLayout.setBackgroundResource(
                item.background
            )
        }
    }
}

private object DiffUtilCallbackStrongWeakSideItem : DiffUtil.ItemCallback<StrongWeakSideItem>() {
    override fun areItemsTheSame(
        oldItem: StrongWeakSideItem,
        newItem: StrongWeakSideItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StrongWeakSideItem,
        newItem: StrongWeakSideItem
    ): Boolean {
        return oldItem == newItem
    }
}
