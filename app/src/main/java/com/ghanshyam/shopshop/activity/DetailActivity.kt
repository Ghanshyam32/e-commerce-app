package com.ghanshyam.shopshop.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1762.Helper.ManagementCart
import com.ghanshyam.shopshop.Model.ItemsModel
import com.ghanshyam.shopshop.Model.Slider
import com.ghanshyam.shopshop.adapter.ProductColorAdapter
import com.ghanshyam.shopshop.adapter.SizeAdapter
import com.ghanshyam.shopshop.adapter.SliderAdapter
import com.ghanshyam.shopshop.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var orderNumber = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)
        getBundle()
        banners()
        initLists()

    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ProductColorAdapter(colorList)
        binding.colorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun banners() {
        val sliderItems = ArrayList<Slider>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(Slider(imageUrl))
        }
        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1
        if (sliderItems.size > 1) {
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!
        binding.title.text = item.title
        binding.description.text = item.description
        binding.itemPrice.text = "$" + item.price
        binding.rating.text = "${item.rating} Rating"
        binding.addToCart.setOnClickListener {
            item.itemsInCart = orderNumber
            managementCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.cart.setOnClickListener {
        }

    }
}