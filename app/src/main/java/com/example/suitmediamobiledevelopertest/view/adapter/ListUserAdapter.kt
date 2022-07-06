package com.example.suitmediamobiledevelopertest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediamobiledevelopertest.api.UserDataResponse
import com.example.suitmediamobiledevelopertest.databinding.ItemUserBinding

class ListUserAdapter(
    private val onClick: (String) -> Unit
) : PagingDataAdapter<UserDataResponse, ListUserAdapter.ListUserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onClick("${data.firstName} ${data.lastName}")
            }
        }
    }

    class ListUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserDataResponse) {
            Glide.with(this.itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = "${data.firstName} ${data.lastName}"
            binding.tvItemEmail.text = data.email
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDataResponse>() {
            override fun areItemsTheSame(
                oldItem: UserDataResponse,
                newItem: UserDataResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserDataResponse,
                newItem: UserDataResponse
            ): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }
}