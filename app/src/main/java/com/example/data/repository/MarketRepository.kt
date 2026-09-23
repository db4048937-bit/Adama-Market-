package com.example.data.repository

import com.example.data.local.CartItemEntity
import com.example.data.local.MarketDao
import com.example.data.local.OrderEntity
import com.example.data.local.WishlistEntity
import com.example.data.model.Product
import com.example.data.model.ProductCatalog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID

class MarketRepository(private val dao: MarketDao) {

    // --- Product Discovery ---
    fun getAllProducts(): List<Product> = ProductCatalog.PRODUCTS

    fun getProductById(id: String): Product? =
        ProductCatalog.PRODUCTS.find { it.id == id }

    // --- Cart operations ---
    val cartItems: Flow<List<CartItemEntity>> = dao.getAllCartItems()

    suspend fun addToCart(product: Product, quantity: Int = 1, variant: String = "") {
        val existing = dao.getCartItemById(product.id)
        val selectedVariant = if (variant.isNotBlank()) variant else product.variants.firstOrNull() ?: "Standard"
        if (existing != null) {
            dao.updateQuantity(product.id, existing.quantity + quantity)
        } else {
            dao.insertOrUpdateCartItem(
                CartItemEntity(
                    productId = product.id,
                    productName = product.name,
                    priceBirr = product.priceBirr,
                    imageResId = product.imageResId,
                    quantity = quantity,
                    selectedVariant = selectedVariant,
                    category = product.category,
                    sellerName = product.sellerName
                )
            )
        }
    }

    suspend fun updateCartQuantity(productId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            dao.deleteCartItem(productId)
        } else {
            dao.updateQuantity(productId, newQuantity)
        }
    }

    suspend fun removeFromCart(productId: String) {
        dao.deleteCartItem(productId)
    }

    suspend fun clearCart() {
        dao.clearCart()
    }

    // --- Order operations ---
    val allOrders: Flow<List<OrderEntity>> = dao.getAllOrders()

    fun getOrderFlow(orderId: String): Flow<OrderEntity?> = dao.getOrderFlow(orderId)

    suspend fun getOrderById(orderId: String): OrderEntity? = dao.getOrderById(orderId)

    suspend fun placeOrder(
        cartItems: List<CartItemEntity>,
        customerName: String,
        customerPhone: String,
        deliveryAddress: String,
        deliveryArea: String,
        paymentMethod: String,
        paymentReference: String = "",
        discountBirr: Double = 0.0,
        orderNotes: String = ""
    ): OrderEntity {
        val subtotal = cartItems.sumOf { it.priceBirr * it.quantity }
        val total = (subtotal - discountBirr).coerceAtLeast(0.0)
        val summary = cartItems.joinToString(", ") { "${it.quantity}x ${it.productName}" }
        val itemCount = cartItems.sumOf { it.quantity }

        val order = OrderEntity(
            orderId = "ADM-" + UUID.randomUUID().toString().take(6).uppercase(),
            timestamp = System.currentTimeMillis(),
            totalAmountBirr = total,
            subtotalBirr = subtotal,
            deliveryFeeBirr = 0.0,
            discountBirr = discountBirr,
            customerName = customerName,
            customerPhone = customerPhone,
            deliveryAddress = deliveryAddress,
            deliveryArea = deliveryArea,
            paymentMethod = paymentMethod,
            paymentReference = paymentReference,
            currentStage = "CONFIRMED",
            itemsSummary = summary,
            itemCount = itemCount,
            estimatedArrivalMinutes = 0,
            riderName = "Adama Market Admin",
            riderPhone = ProductCatalog.OWNER_PHONE,
            orderNotes = orderNotes
        )

        dao.insertOrder(order)
        dao.clearCart()
        return order
    }

    // --- Wishlist operations ---
    val wishlistIds: Flow<List<String>> = dao.getAllWishlist().map { list -> list.map { it.productId } }

    suspend fun toggleWishlist(productId: String) {
        val current = wishlistIds.first()
        if (current.contains(productId)) {
            dao.removeFromWishlist(productId)
        } else {
            dao.addToWishlist(WishlistEntity(productId = productId))
        }
    }
}
