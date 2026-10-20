package com.example.feature.wardrobe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.FormalityLevel
import com.example.core.ui.components.ClothingItemCard
import com.example.core.ui.components.ColorSwatchPill
import com.example.core.ui.components.StyleBadge
import com.example.feature.outfits.FilterChip
import com.example.ui.theme.Spacing

@Composable
fun WardrobeScreen(
    viewModel: WardrobeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("wardrobe_screen")
    ) {
        // Search Bar
        Box(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.sm)) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("wardrobe_search_input"),
                placeholder = { Text("Search tops, chinos, boots, fabrics...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
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

        // Category Horizontal Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = Spacing.base, vertical = Spacing.xs),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "All Categories",
                selected = uiState.selectedCategory == null,
                onClick = { viewModel.selectCategory(null) }
            )
            ClothingCategory.values().forEach { cat ->
                FilterChip(
                    text = cat.displayName,
                    selected = uiState.selectedCategory == cat,
                    onClick = { viewModel.selectCategory(cat) }
                )
            }
        }

        // Formality Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = Spacing.base, vertical = Spacing.xs),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "Any Formality",
                selected = uiState.selectedFormality == null,
                onClick = { viewModel.selectFormality(null) }
            )
            FormalityLevel.values().forEach { form ->
                FilterChip(
                    text = form.label,
                    selected = uiState.selectedFormality == form,
                    onClick = { viewModel.selectFormality(form) }
                )
            }
        }

        // Count Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.base, vertical = Spacing.xs),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${uiState.filteredItems.size} Pieces in Collection",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Item List
        if (uiState.filteredItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacing.xxl),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No garments match your filters",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Try clearing the search or category filters.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Spacing.base, vertical = Spacing.xs),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.filteredItems, key = { it.id }) { item ->
                    ClothingItemCard(
                        item = item,
                        onFavoriteClick = { viewModel.toggleFavorite(item) },
                        onClick = { viewModel.showItemDetail(item) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(84.dp))
                }
            }
        }
    }

    // Item Detail Dialog
    uiState.selectedItemDetail?.let { item ->
        ItemDetailDialog(
            item = item,
            onDismiss = { viewModel.showItemDetail(null) }
        )
    }
}

@Composable
fun ItemDetailDialog(
    item: ClothingItem,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                StyleBadge(text = item.subcategory)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ColorSwatchPill(colorHex = item.colorHex, size = 18.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${item.colorName} • ${item.colorHex}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                }

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(Spacing.sm),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row {
                        Text(
                            text = "Fabric:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(80.dp)
                        )
                        Text(text = item.material, style = MaterialTheme.typography.bodySmall)
                    }
                    Row {
                        Text(
                            text = "Formality:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(80.dp)
                        )
                        Text(text = item.formality.label, style = MaterialTheme.typography.bodySmall)
                    }
                    Row {
                        Text(
                            text = "Season:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(80.dp)
                        )
                        Text(text = item.season.label, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Close")
            }
        }
    )
}
