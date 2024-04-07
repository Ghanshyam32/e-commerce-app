package com.ghanshyam.shopshop.Model

data class ItemModel(
    var title: String,
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var itemsInCart: Int = 0

)
{
    // Default constructor
    constructor() : this("", "", ArrayList(), ArrayList(), 0.0, 0.0, 0)
}