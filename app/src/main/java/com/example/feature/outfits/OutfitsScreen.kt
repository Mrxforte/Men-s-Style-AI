package com.example.feature.outfits

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.model.ClothingItem
import com.example.core.model.Outfit
import com.example.core.ui.components.ColorSwatchPill
import com.example.core.ui.components.FormalityRating
import com.example.core.ui.components.OutfitCard
import com.example.core.ui.components.SharesheetHelper
import com.example.core.ui.components.StyleBadge
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.NavyDeep
import com.example.ui.theme.Spacing

@Composable
fun OutfitsScreen(
    viewModel: OutfitsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("outfits_screen")
    ) {
        // Tab Row: Curated Catalog vs Outfit Combination Engine
        TabRow(
            selectedTabIndex = uiState.selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = uiState.selectedTab == 0,
                onClick = { viewModel.selectTab(0) },
                text = {
                    Text(
                        text = "Curated Catalog",
                        fontWeight = if (uiState.selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                    )
                },
                modifier = Modifier.testTag("catalog_tab")
            )
            Tab(
                selected = uiState.selectedTab == 1,
                onClick = { viewModel.selectTab(1) },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = if (uiState.selectedTab == 1) GoldChampagne else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Outfit Engine",
                            fontWeight = if (uiState.selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                },
                modifier = Modifier.testTag("generator_tab")
            )
        }

        if (uiState.selectedTab == 0) {
            CatalogView(uiState = uiState, viewModel = viewModel)
        } else {
            GeneratorView(uiState = uiState, viewModel = viewModel)
        }
    }
}

@Composable
fun CatalogView(
    uiState: OutfitsUiState,
    viewModel: OutfitsViewModel
) {
    val occasions = listOf("All", "Office", "Date Night", "Weekend", "Wedding Guest", "Evening")
    val styles = listOf("All", "Old Money", "Quiet Luxury", "Smart Casual", "Minimalist", "Streetwear")

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Search Bar
        item {
            Box(modifier = Modifier.padding(Spacing.base)) {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = { query ->
                        viewModel.setCatalogFilters(uiState.selectedOccasion, uiState.selectedStyle, query)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("outfits_search_input"),
                    placeholder = { Text("Search by garment, color, or style...") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = {
                                viewModel.setCatalogFilters(uiState.selectedOccasion, uiState.selectedStyle, "")
                            }) {
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

        // Occasion Filters
        item {
            Column(modifier = Modifier.padding(bottom = Spacing.sm)) {
                Text(
                    text = "OCCASION",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = Spacing.base, vertical = 2.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = Spacing.base),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    occasions.forEach { occ ->
                        val selected = uiState.selectedOccasion == occ
                        FilterChip(
                            text = occ,
                            selected = selected,
                            onClick = {
                                viewModel.setCatalogFilters(occ, uiState.selectedStyle, uiState.searchQuery)
                            }
                        )
                    }
                }
            }
        }

        // Style Filters
        item {
            Column(modifier = Modifier.padding(bottom = Spacing.md)) {
                Text(
                    text = "AESTHETIC / STYLE",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = Spacing.base, vertical = 2.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = Spacing.base),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    styles.forEach { style ->
                        val selected = uiState.selectedStyle == style
                        FilterChip(
                            text = style,
                            selected = selected,
                            onClick = {
                                viewModel.setCatalogFilters(uiState.selectedOccasion, style, uiState.searchQuery)
                            }
                        )
                    }
                }
            }
        }

        // Results count
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.base, vertical = Spacing.xs),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${uiState.filteredCatalog.size} Outfits Available",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // List of Outfits
        if (uiState.filteredCatalog.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.xxl),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No matching outfits found",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Try adjusting your filters or search terms.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(uiState.filteredCatalog, key = { it.id }) { outfit ->
                Box(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.xs)) {
                    OutfitCard(
                        outfit = outfit,
                        onFavoriteClick = { viewModel.toggleFavorite(outfit) }
                    )
                }
            }
        }
    }
}

@Composable
fun GeneratorView(
    uiState: OutfitsUiState,
    viewModel: OutfitsViewModel
) {
    val context = LocalContext.current
    val currentOutfit = uiState.generatedOutfit

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = Spacing.base, vertical = Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        // Explanatory Header
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    0.75.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                )
            ) {
                Column(modifier = Modifier.padding(Spacing.base)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = GoldChampagne,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Outfit Combination Engine",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Deterministic, explainable menswear color harmony. Lock items you like, exclude colors you dislike, and generate the perfect formula.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Color Exclusions Bar
        item {
            Column {
                Text(
                    text = "EXCLUDE UNWANTED COLORS",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(Spacing.xs))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.availableColors.forEach { (name, hex) ->
                        val isExcluded = uiState.excludedColors.contains(hex)
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isExcluded) Color.Red.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface)
                                .border(
                                    0.75.dp,
                                    if (isExcluded) Color.Red.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable { viewModel.toggleColorExclusion(hex) }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ColorSwatchPill(colorHex = hex, size = 12.dp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = name,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isExcluded) Color.Red else MaterialTheme.colorScheme.onSurface
                            )
                            if (isExcluded) {
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Excluded",
                                    tint = Color.Red,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Active Slots with Locking
        if (currentOutfit != null) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "FORMULA PIECES (TAP LOCK TO KEEP AN ITEM)",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    LockableSlotCard(
                        role = "TOP GARMENT",
                        item = currentOutfit.top,
                        isLocked = uiState.lockedSlots.top != null,
                        onToggleLock = { viewModel.toggleLockTop() }
                    )

                    LockableSlotCard(
                        role = "BOTTOM / TROUSERS",
                        item = currentOutfit.bottom,
                        isLocked = uiState.lockedSlots.bottom != null,
                        onToggleLock = { viewModel.toggleLockBottom() }
                    )

                    LockableSlotCard(
                        role = "FOOTWEAR",
                        item = currentOutfit.shoes,
                        isLocked = uiState.lockedSlots.shoes != null,
                        onToggleLock = { viewModel.toggleLockShoes() }
                    )

                    if (currentOutfit.outerwear != null) {
                        LockableSlotCard(
                            role = "OUTER LAYER",
                            item = currentOutfit.outerwear,
                            isLocked = uiState.lockedSlots.outerwear != null,
                            onToggleLock = { viewModel.toggleLockOuterwear() }
                        )
                    }
                }
            }

            // Styling Explanation & Fit Guidance
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(Spacing.base)) {
                        Text(
                            text = "STYLING RATIONALE",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentOutfit.stylingNotes,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(Spacing.sm))
                        Text(
                            text = "FIT GUIDANCE",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentOutfit.fitGuidance,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Action Buttons: Regenerate & Save & Share
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { viewModel.regenerateOutfit() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("regenerate_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Regenerate Other Pieces", fontWeight = FontWeight.Bold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.saveCurrentGeneratedOutfit() },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("save_generated_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (uiState.isSavedSuccess) GoldChampagne else MaterialTheme.colorScheme.secondary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = if (uiState.isSavedSuccess) Icons.Default.Check else Icons.Default.Bookmark,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (uiState.isSavedSuccess) "Saved!" else "Save Outfit")
                        }

                        Button(
                            onClick = { SharesheetHelper.shareOutfit(context, currentOutfit) },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("share_generated_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Share", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}

@Composable
fun LockableSlotCard(
    role: String,
    item: ClothingItem,
    isLocked: Boolean,
    onToggleLock: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = if (isLocked) 1.5.dp else 0.75.dp,
                color = if (isLocked) GoldChampagne else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                shape = RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isLocked) GoldChampagne.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = Spacing.md, vertical = Spacing.sm)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorSwatchPill(colorHex = item.colorHex, size = 16.dp)
            Spacer(modifier = Modifier.width(Spacing.sm))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = role,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = if (isLocked) GoldChampagne else MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${item.name} (${item.colorName})",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
            }

            IconButton(
                onClick = onToggleLock,
                modifier = Modifier
                    .size(36.dp)
                    .testTag("lock_slot_${role.lowercase().replace(" ", "_")}")
            ) {
                Icon(
                    imageVector = if (isLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                    contentDescription = if (isLocked) "Locked" else "Unlocked",
                    tint = if (isLocked) GoldChampagne else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .border(
                0.75.dp,
                if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("chip_${text.lowercase().replace(" ", "_")}")
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            ),
            color = contentColor
        )
    }
}
