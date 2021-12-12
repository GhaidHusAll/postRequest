package com.example.poastreq_ghh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poastreq_ghh.databinding.ItemBinding

class MainAdapter(private val list: ArrayList<UserItem>): RecyclerView.Adapter<MainAdapter.Holder>() {
    class Holder(val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       val user = list[position]
        holder.binding.apply {
            tvName.text = user.name
            tvLocation.text = user.location
        }
    }

    override fun getItemCount() = list.size
}