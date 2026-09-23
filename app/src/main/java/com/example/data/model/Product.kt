package com.example.data.model

data class Product(
    val id: String,
    val name: String,
    val category: String, // "Phone", "Car", "Home", "Electric"
    val priceBirr: Double,
    val originalPriceBirr: Double? = null,
    val rating: Float = 4.9f,
    val reviewsCount: Int = 42,
    val imageResId: Int,
    val description: String,
    val locationInAdama: String,
    val tags: List<String> = emptyList(),
    val variants: List<String> = emptyList(),
    val sellerName: String = "Adama Market Official",
    val sellerPhone: String = "+251992558349",
    val sellerTelegram: String = "https://t.me/Adama_Shopiify",
    val isHotDeal: Boolean = false,
    val condition: String = "Brand New / Verified"
)
