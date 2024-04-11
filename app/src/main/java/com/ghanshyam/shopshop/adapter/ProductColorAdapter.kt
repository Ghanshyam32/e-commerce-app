package com.ghanshyam.shopshop.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghanshyam.shopshop.Model.Brand
import com.ghanshyam.shopshop.R
import com.ghanshyam.shopshop.databinding.ViewholderBrandBinding
import com.ghanshyam.shopshop.databinding.ViewholderColorBinding

class ProductColorAdapter(val items: MutableList<String>) :
    RecyclerView.Adapter<ProductColorAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPositions = -1
    private lateinit var context: Context

    inner class Viewholder(val binding: ViewholderColorBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductColorAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ProductColorAdapter.Viewholder, position: Int) {

        Glide.with(holder.itemView.context).load(items[position]).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPositions = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPositions)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size


}