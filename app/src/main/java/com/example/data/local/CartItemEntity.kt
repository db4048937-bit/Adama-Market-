package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val priceBirr: Double,
    val imageResId: Int,
    val quantity: Int,
    val selectedVariant: String,
    val category: String,
    val sellerName: String = "Adama Shopify Official"
)
