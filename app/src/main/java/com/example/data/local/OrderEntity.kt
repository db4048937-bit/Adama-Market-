package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val orderId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val totalAmountBirr: Double,
    val subtotalBirr: Double,
    val deliveryFeeBirr: Double,
    val discountBirr: Double,
    val customerName: String,
    val customerPhone: String,
    val deliveryAddress: String,
    val deliveryArea: String,
    val paymentMethod: String,
    val paymentReference: String,
    val currentStage: String, // Matches DeliveryStage enum name
    val itemsSummary: String,
    val itemCount: Int,
    val estimatedArrivalMinutes: Int = 35,
    val riderName: String = "Dawit T.",
    val riderPhone: String = "+251 91 234 5678",
    val orderNotes: String = ""
)
