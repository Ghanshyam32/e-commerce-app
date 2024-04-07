package com.ghanshyam.shopshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.ghanshyam.shopshop.Model.Brand
import com.ghanshyam.shopshop.Model.Slider
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<Slider>>()
    val banners: LiveData<List<Slider>> = _banner

    private val _brand = MutableLiveData<MutableList<Brand>>()
    val brands: LiveData<MutableList<Brand>> = _brand

    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<Slider>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(Slider::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun loadBrand() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<Brand>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(Brand::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}