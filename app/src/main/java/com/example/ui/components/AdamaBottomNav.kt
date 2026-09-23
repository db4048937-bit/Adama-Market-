package com.example.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.viewmodel.ScreenTab

@Composable
fun AdamaBottomNav(
    currentTab: ScreenTab,
    cartItemCount: Int,
    onTabSelected: (ScreenTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        // Market (Home)
        NavigationBarItem(
            selected = currentTab == ScreenTab.HOME,
            onClick = { onTabSelected(ScreenTab.HOME) },
            icon = {
                Icon(
                    imageVector = if (currentTab == ScreenTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Market"
                )
            },
            label = {
                Text(
                    text = "Market",
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == ScreenTab.HOME) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Discover (Categories: Phone, Car, Home, Electric)
        NavigationBarItem(
            selected = currentTab == ScreenTab.CATEGORIES,
            onClick = { onTabSelected(ScreenTab.CATEGORIES) },
            icon = {
                Icon(
                    imageVector = if (currentTab == ScreenTab.CATEGORIES) Icons.Filled.Explore else Icons.Outlined.Explore,
                    contentDescription = "Categories"
                )
            },
            label = {
                Text(
                    text = "Categories",
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == ScreenTab.CATEGORIES) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Inquiries / Cart
        NavigationBarItem(
            selected = currentTab == ScreenTab.CART,
            onClick = { onTabSelected(ScreenTab.CART) },
            icon = {
                BadgedBox(
                    badge = {
                        if (cartItemCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                Text(
                                    text = cartItemCount.toString(),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentTab == ScreenTab.CART) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                        contentDescription = "Inquiries"
                    )
                }
            },
            label = {
                Text(
                    text = "Inquiries",
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == ScreenTab.CART) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Saved (Wishlist)
        NavigationBarItem(
            selected = currentTab == ScreenTab.FAVORITES,
            onClick = { onTabSelected(ScreenTab.FAVORITES) },
            icon = {
                Icon(
                    imageVector = if (currentTab == ScreenTab.FAVORITES) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Saved"
                )
            },
            label = {
                Text(
                    text = "Saved",
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == ScreenTab.FAVORITES) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Orders
        NavigationBarItem(
            selected = currentTab == ScreenTab.ORDERS,
            onClick = { onTabSelected(ScreenTab.ORDERS) },
            icon = {
                Icon(
                    imageVector = if (currentTab == ScreenTab.ORDERS) Icons.Filled.ReceiptLong else Icons.Outlined.ReceiptLong,
                    contentDescription = "Orders"
                )
            },
            label = {
                Text(
                    text = "Orders",
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == ScreenTab.ORDERS) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}
