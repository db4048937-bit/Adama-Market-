package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AdamaBottomNav
import com.example.ui.components.AdamaTopBar
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CheckoutDialog
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OrdersHistoryScreen
import com.example.ui.screens.ProductDetailSheet
import com.example.ui.screens.WishlistScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.CheckoutState
import com.example.ui.viewmodel.MarketViewModel
import com.example.ui.viewmodel.ScreenTab

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AdamaMarketApp()
            }
        }
    }
}

@Composable
fun AdamaMarketApp(viewModel: MarketViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val filteredProducts by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val wishlistIds by viewModel.wishlistIds.collectAsStateWithLifecycle()
    val orders by viewModel.orders.collectAsStateWithLifecycle()
    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val checkoutState by viewModel.checkoutState.collectAsStateWithLifecycle()
    val appliedPromoCode by viewModel.appliedPromoCode.collectAsStateWithLifecycle()
    val discountAmount by viewModel.discountAmount.collectAsStateWithLifecycle()

    val cartTotalCount = cartItems.sumOf { it.quantity }
    val cartSubtotal = cartItems.sumOf { it.priceBirr * it.quantity }
    val finalTotal = (cartSubtotal - discountAmount).coerceAtLeast(0.0)

    Scaffold(
        topBar = {
            AdamaTopBar(
                onCallAdmin = { viewModel.dialOwnerPhone() },
                onOpenTelegram = { viewModel.openTelegramChannel() },
                onSearchClick = { viewModel.navigateToTab(ScreenTab.CATEGORIES) }
            )
        },
        bottomBar = {
            AdamaBottomNav(
                currentTab = currentTab,
                cartItemCount = cartTotalCount,
                onTabSelected = { viewModel.navigateToTab(it) },
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentTab, label = "tab_transition") { tab ->
                when (tab) {
                    ScreenTab.HOME -> {
                        HomeScreen(
                            products = filteredProducts,
                            wishlistIds = wishlistIds,
                            searchQuery = searchQuery,
                            selectedCategory = selectedCategory,
                            onSearchChange = { viewModel.onSearchQueryChanged(it) },
                            onCategoryChange = { viewModel.onCategorySelected(it) },
                            onProductClick = { viewModel.selectProduct(it) },
                            onCallSeller = { phone -> viewModel.dialPhoneNumber(phone) },
                            onCallOwner = { viewModel.dialOwnerPhone() },
                            onToggleWishlist = { viewModel.toggleWishlist(it) },
                            onInquireTelegram = { viewModel.inquireProductOnTelegram(it) },
                            onOpenTelegramChannel = { viewModel.openTelegramChannel() }
                        )
                    }

                    ScreenTab.CATEGORIES -> {
                        ExploreScreen(
                            products = filteredProducts,
                            wishlistIds = wishlistIds,
                            selectedCategory = selectedCategory,
                            onCategoryChange = { viewModel.onCategorySelected(it) },
                            onProductClick = { viewModel.selectProduct(it) },
                            onCallSeller = { phone -> viewModel.dialPhoneNumber(phone) },
                            onToggleWishlist = { viewModel.toggleWishlist(it) },
                            onInquireTelegram = { viewModel.inquireProductOnTelegram(it) }
                        )
                    }

                    ScreenTab.CART -> {
                        CartScreen(
                            cartItems = cartItems,
                            appliedPromoCode = appliedPromoCode,
                            discountAmount = discountAmount,
                            onUpdateQuantity = { id, qty -> viewModel.updateCartQuantity(id, qty) },
                            onRemoveItem = { viewModel.removeFromCart(it) },
                            onClearCart = { viewModel.clearCart() },
                            onApplyPromo = { code, subtotal -> viewModel.applyPromoCode(code, subtotal) },
                            onRemovePromo = { viewModel.removePromoCode() },
                            onProceedToCheckout = { viewModel.startCheckout() },
                            onCallAdmin = { viewModel.dialOwnerPhone() },
                            onOpenTelegram = { viewModel.openTelegramChannel() },
                            onContinueShopping = { viewModel.navigateToTab(ScreenTab.HOME) }
                        )
                    }

                    ScreenTab.FAVORITES -> {
                        WishlistScreen(
                            products = filteredProducts,
                            wishlistIds = wishlistIds,
                            onProductClick = { viewModel.selectProduct(it) },
                            onCallSeller = { phone -> viewModel.dialPhoneNumber(phone) },
                            onToggleWishlist = { viewModel.toggleWishlist(it) },
                            onInquireTelegram = { viewModel.inquireProductOnTelegram(it) },
                            onStartShopping = { viewModel.navigateToTab(ScreenTab.HOME) }
                        )
                    }

                    ScreenTab.ORDERS -> {
                        OrdersHistoryScreen(
                            orders = orders,
                            onCallAdmin = { viewModel.dialOwnerPhone() },
                            onOpenTelegram = { viewModel.openTelegramChannel() },
                            onStartShopping = { viewModel.navigateToTab(ScreenTab.HOME) }
                        )
                    }
                }
            }

            // Product Detail Bottom Sheet
            selectedProduct?.let { product ->
                ProductDetailSheet(
                    product = product,
                    isFavorite = wishlistIds.contains(product.id),
                    onDismiss = { viewModel.selectProduct(null) },
                    onCallSeller = { phone -> viewModel.dialPhoneNumber(phone) },
                    onAddToCart = { qty, variant ->
                        viewModel.addToCart(product, qty, variant)
                    },
                    onToggleWishlist = { viewModel.toggleWishlist(product.id) },
                    onInquireTelegram = { viewModel.inquireProductOnTelegram(product) }
                )
            }

            // Checkout Dialog & Flow
            if (checkoutState !is CheckoutState.Idle) {
                CheckoutDialog(
                    checkoutState = checkoutState,
                    totalAmountBirr = finalTotal,
                    onDismiss = { viewModel.cancelCheckout() },
                    onSubmitOrder = { name, phone, address, area, method, notes ->
                        viewModel.processPaymentAndOrder(
                            customerName = name,
                            customerPhone = phone,
                            deliveryAddress = address,
                            deliveryArea = area,
                            paymentMethod = method,
                            orderNotes = notes
                        )
                    },
                    onCallAdmin = { viewModel.dialOwnerPhone() },
                    onOpenTelegram = { viewModel.openTelegramChannel() },
                    onViewOrders = { viewModel.dismissSuccessAndGoToOrders() }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(text = "Hello $name!", modifier = modifier)
}
