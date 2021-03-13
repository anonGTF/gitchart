package com.galih.gitchart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.galih.gitchart.databinding.ItemUserBinding

class UserAdapter (
    val users: List<User>
    ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        private lateinit var onItemClickCallback: OnItemClickCallback

        inner class UserViewHolder(val itemBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemBinding.apply {
            tvUserName.text = users[position].name
            tvUserUsername.text = users[position].username
            tvUserLocation.text = users[position].location
        }

        Glide.with(holder.itemView.context)
            .load(users[position].avatarId)
            .apply(RequestOptions().override(50))
            .into(holder.itemBinding.imgUser)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(users[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = users.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data : User)
    }
}