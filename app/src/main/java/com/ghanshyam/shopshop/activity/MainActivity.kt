package com.ghanshyam.shopshop.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.ghanshyam.shopshop.Model.Slider
import com.ghanshyam.shopshop.SliderAdapter
import com.ghanshyam.shopshop.ViewModel.MainViewModel
import com.ghanshyam.shopshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
    }

    private fun initBanner() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBar.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<Slider>) {
        binding.viewPager.adapter = SliderAdapter(images, binding.viewPager)
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.offscreenPageLimit = 4
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager.setPageTransformer(compositePageTransformer)
        if(images.size>1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPager)
        }
    }

}