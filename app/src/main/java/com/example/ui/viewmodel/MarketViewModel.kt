package com.example.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.CartItemEntity
import com.example.data.local.OrderEntity
import com.example.data.model.PaymentMethod
import com.example.data.model.Product
import com.example.data.model.ProductCatalog
import com.example.data.repository.MarketRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ScreenTab {
    HOME,
    CATEGORIES,
    FAVORITES,
    CART,
    ORDERS
}

sealed class CheckoutState {
    object Idle : CheckoutState()
    data class Processing(val stepText: String) : CheckoutState()
    data class Success(val order: OrderEntity) : CheckoutState()
    data class Error(val message: String) : CheckoutState()
}

class MarketViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MarketRepository
    val cartItems: StateFlow<List<CartItemEntity>>
    val wishlistIds: StateFlow<List<String>>
    val orders: StateFlow<List<OrderEntity>>

    init {
        val database = AppDatabase.getInstance(application)
        repository = MarketRepository(database.marketDao())
        cartItems = repository.cartItems.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
        wishlistIds = repository.wishlistIds.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
        orders = repository.allOrders.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    private val _currentTab = MutableStateFlow(ScreenTab.HOME)
    val currentTab: StateFlow<ScreenTab> = _currentTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All Items")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _checkoutState = MutableStateFlow<CheckoutState>(CheckoutState.Idle)
    val checkoutState: StateFlow<CheckoutState> = _checkoutState.asStateFlow()

    private val _appliedPromoCode = MutableStateFlow<String?>(null)
    val appliedPromoCode: StateFlow<String?> = _appliedPromoCode.asStateFlow()

    private val _discountAmount = MutableStateFlow(0.0)
    val discountAmount: StateFlow<Double> = _discountAmount.asStateFlow()

    // Filtered products based on search query and category
    val filteredProducts: StateFlow<List<Product>> = combine(
        _searchQuery,
        _selectedCategory
    ) { query, category ->
        ProductCatalog.PRODUCTS.filter { product ->
            val matchesCategory = category == "All Items" || product.category.equals(category, ignoreCase = true)
            val matchesQuery = query.isBlank() ||
                    product.name.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true) ||
                    product.locationInAdama.contains(query, ignoreCase = true) ||
                    product.tags.any { it.contains(query, ignoreCase = true) }
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductCatalog.PRODUCTS)

    fun navigateToTab(tab: ScreenTab) {
        _currentTab.value = tab
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
    }

    fun addToCart(product: Product, quantity: Int = 1, variant: String = "") {
        viewModelScope.launch {
            repository.addToCart(product, quantity, variant)
            Toast.makeText(
                getApplication(),
                "Saved '${product.name}' to Inquiries",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun updateCartQuantity(productId: String, quantity: Int) {
        viewModelScope.launch {
            repository.updateCartQuantity(productId, quantity)
        }
    }

    fun removeFromCart(productId: String) {
        viewModelScope.launch {
            repository.removeFromCart(productId)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            _appliedPromoCode.value = null
            _discountAmount.value = 0.0
        }
    }

    fun toggleWishlist(productId: String) {
        viewModelScope.launch {
            repository.toggleWishlist(productId)
        }
    }

    fun applyPromoCode(code: String, subtotal: Double): Boolean {
        return when (code.trim().uppercase()) {
            "ADAMA10" -> {
                _appliedPromoCode.value = "ADAMA10 (-10%)"
                _discountAmount.value = subtotal * 0.10
                true
            }
            "WELCOME5" -> {
                _appliedPromoCode.value = "WELCOME5 (-5%)"
                _discountAmount.value = subtotal * 0.05
                true
            }
            else -> false
        }
    }

    fun removePromoCode() {
        _appliedPromoCode.value = null
        _discountAmount.value = 0.0
    }

    fun startCheckout() {
        _checkoutState.value = CheckoutState.Processing("Preparing booking details...")
        viewModelScope.launch {
            delay(400)
            _checkoutState.value = CheckoutState.Idle
        }
    }

    fun cancelCheckout() {
        _checkoutState.value = CheckoutState.Idle
    }

    fun processPaymentAndOrder(
        customerName: String,
        customerPhone: String,
        deliveryAddress: String,
        deliveryArea: String,
        paymentMethod: PaymentMethod,
        orderNotes: String
    ) {
        viewModelScope.launch {
            _checkoutState.value = CheckoutState.Processing("Connecting to ${paymentMethod.title} Gateway...")
            delay(900)

            _checkoutState.value = CheckoutState.Processing("Verifying order in Adama...")
            delay(900)

            val currentCart = cartItems.value
            val order = repository.placeOrder(
                cartItems = currentCart,
                customerName = customerName,
                customerPhone = customerPhone,
                deliveryAddress = deliveryAddress,
                deliveryArea = deliveryArea,
                paymentMethod = paymentMethod.title,
                paymentReference = "REF-${(1000..9999).random()}",
                discountBirr = _discountAmount.value,
                orderNotes = orderNotes
            )

            _appliedPromoCode.value = null
            _discountAmount.value = 0.0

            _checkoutState.value = CheckoutState.Success(order)
        }
    }

    fun dismissSuccessAndGoToOrders() {
        _checkoutState.value = CheckoutState.Idle
        _currentTab.value = ScreenTab.ORDERS
    }

    fun dialOwnerPhone() {
        dialPhoneNumber(ProductCatalog.OWNER_PHONE)
    }

    fun dialPhoneNumber(phone: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${phone.trim()}")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            getApplication<Application>().startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(getApplication(), "Dialer not available: $phone", Toast.LENGTH_SHORT).show()
        }
    }

    fun openTelegramChannel() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ProductCatalog.TELEGRAM_CHANNEL)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            getApplication<Application>().startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(getApplication(), "Could not open Telegram: ${ProductCatalog.TELEGRAM_CHANNEL}", Toast.LENGTH_SHORT).show()
        }
    }

    fun inquireProductOnTelegram(product: Product) {
        try {
            val msg = Uri.encode("Hello Adama Market! I am inquiring about '${product.name}' (${product.priceBirr} ETB) posted on the app.")
            val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${ProductCatalog.TELEGRAM_CHANNEL}?text=$msg")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            getApplication<Application>().startActivity(telegramIntent)
        } catch (e: Exception) {
            openTelegramChannel()
        }
    }
}
