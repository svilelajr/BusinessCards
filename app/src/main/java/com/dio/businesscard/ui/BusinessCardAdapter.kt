package com.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dio.businesscard.data.BusinessCard
import com.dio.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter: androidx.recyclerview.widget.ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {


    var listenerShare: (View) -> Unit = {


    }



    inner class ViewHolder(
        private val binding : ItemBusinessCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: BusinessCard){
            binding.tvName.text = item.name
            binding.tvEmail.text = item.email
            binding.tvPhone.text = item.phone
            binding.tvCompany.text = item.company
            binding.mcvContent.setBackgroundColor(Color.parseColor(item.background))
            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

}

class DiffCallback: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
       return oldItem.id == newItem.id
    }


}