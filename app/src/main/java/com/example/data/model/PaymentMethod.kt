package com.example.data.model

enum class PaymentMethod(
    val title: String,
    val subtitle: String,
    val iconName: String,
    val feeDescription: String
) {
    TELEBIRR(
        title = "Telebirr",
        subtitle = "Fast mobile wallet payment (+251 9...)",
        iconName = "telebirr",
        feeDescription = "Instant confirmation, zero transfer fee"
    ),
    CBE_BIRR(
        title = "CBE Birr",
        subtitle = "Commercial Bank of Ethiopia direct payment",
        iconName = "cbe",
        feeDescription = "Direct bank debit or CBE Birr account"
    ),
    CHAPA(
        title = "Chapa / Cards",
        subtitle = "Visa, Mastercard & Local Ethiopian bank cards",
        iconName = "chapa",
        feeDescription = "Encrypted 256-bit secure gateway"
    ),
    CASH_ON_DELIVERY(
        title = "Cash on Delivery (COD)",
        subtitle = "Pay cash or Telebirr upon rider handover",
        iconName = "cash",
        feeDescription = "Inspect product first in Adama"
    )
}

enum class DeliveryStage(
    val title: String,
    val description: String,
    val progress: Float
) {
    CONFIRMED("Order Confirmed", "Payment verified & sent to Adama Hub merchant", 0.15f),
    PREPARING("Preparing Package", "Inspecting and packaging your items with care", 0.40f),
    PICKED_UP("Picked Up by Courier", "Rider Dawit T. has collected your parcel", 0.65f),
    IN_TRANSIT("Out for Delivery", "Express courier moving along Adama Main Ave", 0.85f),
    DELIVERED("Delivered", "Handed over safely to recipient", 1.0f)
}
