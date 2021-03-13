package com.galih.gitchart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.galih.gitchart.databinding.ItemAccountBinding

class AccountAdapter (
    val accounts: List<Account>
    ) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

        private lateinit var onItemClickCallback: OnItemClickCallback

        inner class AccountViewHolder(val itemBinding: ItemAccountBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemBinding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.itemBinding.apply {
            tvAccountName.text = accounts[position].name
            tvAccountUsername.text = accounts[position].username
            tvAccountLocation.text = accounts[position].location
        }

        customGlider(holder.itemView.context, accounts[position].avatar, 150, holder.itemBinding.imgAccount)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(accounts[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = accounts.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data : Account)
    }
}