package com.example.betmasters

sealed class ItemRedeem {
    data class BigItem(val name: String, val price: Int, val img: Int) : ItemRedeem()
    data class ThinItem(val name: String, val price: Int, val img: Int) : ItemRedeem()
    data class HorizontalRv(val items: List<Card>) : ItemRedeem()
}