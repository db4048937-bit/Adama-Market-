package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Product
import com.example.data.model.ProductCatalog
import com.example.ui.components.CategoryLogosRow
import com.example.ui.components.ProductCard

@Composable
fun ExploreScreen(
    products: List<Product>,
    wishlistIds: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    onCallSeller: (String) -> Unit,
    onToggleWishlist: (String) -> Unit,
    onInquireTelegram: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLocation by remember { mutableStateOf("All Adama") }
    var searchQuery by remember { mutableStateOf("") }

    val locations = listOf("All Adama") + ProductCatalog.ADAMA_LOCATIONS

    val filteredList = products.filter { product ->
        val matchesCategory = selectedCategory == "All Items" || product.category.equals(selectedCategory, ignoreCase = true)
        val matchesLocation = selectedLocation == "All Adama" || product.locationInAdama.contains(selectedLocation.substringBefore(" "), ignoreCase = true)
        val matchesSearch = searchQuery.isBlank() ||
                product.name.contains(searchQuery, ignoreCase = true) ||
                product.description.contains(searchQuery, ignoreCase = true) ||
                product.tags.any { it.contains(searchQuery, ignoreCase = true) }
        matchesCategory && matchesLocation && matchesSearch
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("explore_screen_column"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Search & Filter header
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Explore Categories & Locations",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Verified Phone, Car, Home & Electric listings across Adama",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth().testTag("explore_search_field"),
                    placeholder = { Text("Filter items in Adama...", fontSize = 13.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    singleLine = true
                )
            }
        }

        // Category Logos Row
        item {
            Box(modifier = Modifier.padding(bottom = 8.dp)) {
                CategoryLogosRow(
                    selectedCategory = selectedCategory,
                    onSelectCategory = onCategoryChange
                )
            }
        }

        // Location Chips
        item {
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Adama Hub Location",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(locations) { loc ->
                        val isSelected = selectedLocation == loc
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedLocation = loc },
                            label = { Text(loc, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }

        // Header Results Count
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Found ${filteredList.size} Listings",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // 2-column Grid
        val chunks = filteredList.chunked(2)
        items(chunks) { pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ProductCard(
                        product = pair[0],
                        isFavorite = wishlistIds.contains(pair[0].id),
                        onProductClick = { onProductClick(pair[0]) },
                        onCallSeller = { onCallSeller(pair[0].sellerPhone) },
                        onToggleWishlist = { onToggleWishlist(pair[0].id) },
                        onInquireTelegram = { onInquireTelegram(pair[0]) }
                    )
                }
                if (pair.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductCard(
                            product = pair[1],
                            isFavorite = wishlistIds.contains(pair[1].id),
                            onProductClick = { onProductClick(pair[1]) },
                            onCallSeller = { onCallSeller(pair[1].sellerPhone) },
                            onToggleWishlist = { onToggleWishlist(pair[1].id) },
                            onInquireTelegram = { onInquireTelegram(pair[1]) }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
