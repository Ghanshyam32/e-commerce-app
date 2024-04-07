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

class BrandAdapter(val items: MutableList<Brand>) :
    RecyclerView.Adapter<BrandAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPositions = -1
    private lateinit var context: Context

    class Viewholder(val binding: ViewholderBrandBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        val items = items[position]
        holder.binding.title.text = items.title

        Glide.with(holder.itemView.context).load(items.picUrl).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPositions = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPositions)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.black_bg2)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )
            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.black))
            )
            holder.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size


}