package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class WishlistEntity(
    @PrimaryKey
    val productId: String,
    val addedAt: Long = System.currentTimeMillis()
)
