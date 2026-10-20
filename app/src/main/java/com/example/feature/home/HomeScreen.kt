package com.example.feature.home

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
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DryCleaning
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.model.ClothingCategory
import com.example.core.model.Outfit
import com.example.core.ui.components.ColorCombinationCard
import com.example.core.ui.components.OutfitCard
import com.example.core.ui.components.PoseCard
import com.example.core.ui.components.SectionHeader
import com.example.core.ui.components.StyleBadge
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.NavyCharcoal
import com.example.ui.theme.NavyDeep
import com.example.ui.theme.Spacing

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToOutfits: () -> Unit,
    onNavigateToWardrobe: (ClothingCategory?) -> Unit,
    onNavigateToColors: () -> Unit,
    onNavigateToStyleGuide: () -> Unit,
    onNavigateToPoses: () -> Unit,
    onNavigateToAiStylist: () -> Unit,
    onNavigateToSaved: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Luxury Editorial Header & Branding
        item {
            EditorialHeroBanner(
                savedCount = uiState.savedCount,
                onAiStylistClick = onNavigateToAiStylist,
                onSavedClick = onNavigateToSaved
            )
        }

        // Quick Wardrobe Categories Carousel
        item {
            Column(modifier = Modifier.padding(top = Spacing.md)) {
                Text(
                    text = "WARDROBE DEPARTMENTS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = Spacing.base)
                )

                Spacer(modifier = Modifier.height(Spacing.sm))

                WardrobeQuickCategories(onCategoryClick = onNavigateToWardrobe)
            }
        }

        // Featured Outfit of the Day
        item {
            uiState.featuredOutfit?.let { featured ->
                Column(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.md)) {
                    SectionHeader(
                        title = "Outfit of the Day",
                        subtitle = "Curated sartorial excellence",
                        actionLabel = "All Outfits",
                        onActionClick = onNavigateToOutfits
                    )

                    OutfitCard(
                        outfit = featured,
                        onFavoriteClick = { viewModel.toggleFavorite(featured) },
                        onClick = onNavigateToOutfits
                    )
                }
            }
        }

        // Color Explorer Feature Spotlight
        item {
            Column(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.sm)) {
                SectionHeader(
                    title = "Color Combinations",
                    subtitle = "Harmonious menswear color palettes",
                    actionLabel = "Explore All",
                    onActionClick = onNavigateToColors
                )

                uiState.trendingCombos.firstOrNull()?.let { combo ->
                    ColorCombinationCard(
                        combo = combo,
                        onClick = onNavigateToColors
                    )
                }
            }
        }

        // Body Language & Posture Spotlight
        item {
            uiState.featuredPose?.let { pose ->
                Column(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.md)) {
                    SectionHeader(
                        title = "Posture & Presence",
                        subtitle = "Natural body language for photos & everyday life",
                        actionLabel = "View Poses",
                        onActionClick = onNavigateToPoses
                    )

                    PoseCard(
                        pose = pose,
                        onClick = onNavigateToPoses
                    )
                }
            }
        }

        // Curated Outfits Section
        item {
            Column(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.sm)) {
                SectionHeader(
                    title = "Editorial Ensembles",
                    subtitle = "Balanced casual, smart-casual, and formal formulas",
                    actionLabel = "See All",
                    onActionClick = onNavigateToOutfits
                )
            }
        }

        items(uiState.curatedOutfits, key = { it.id }) { outfit ->
            Box(modifier = Modifier.padding(horizontal = Spacing.base, vertical = Spacing.xs)) {
                OutfitCard(
                    outfit = outfit,
                    onFavoriteClick = { viewModel.toggleFavorite(outfit) },
                    onClick = onNavigateToOutfits
                )
            }
        }
    }
}

@Composable
fun EditorialHeroBanner(
    savedCount: Int,
    onAiStylistClick: () -> Unit,
    onSavedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NavyDeep,
                        NavyCharcoal
                    )
                )
            )
            .padding(horizontal = Spacing.base, vertical = Spacing.xl)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "MEN'S STYLE AI",
                        style = MaterialTheme.typography.labelSmall.copy(
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = GoldChampagne
                    )
                    Text(
                        text = "The Modern Sartorialist",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }

                if (savedCount > 0) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.12f))
                            .clickable { onSavedClick() }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("saved_shortcut_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = GoldChampagne,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$savedCount Saved",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.md))

            // AI Stylist Callout Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, GoldChampagne.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .clickable { onAiStylistClick() }
                    .testTag("ai_stylist_banner"),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.08f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(Spacing.base),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(GoldChampagne.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = GoldChampagne,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(Spacing.md))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "AI Personal Stylist",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                        Text(
                            text = "Custom outfit formulas tailored to your occasion, weather & style.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WardrobeQuickCategories(
    onCategoryClick: (ClothingCategory?) -> Unit
) {
    val categories = listOf(
        Triple(ClothingCategory.TOPS, "Tops", Icons.Default.Checkroom),
        Triple(ClothingCategory.BOTTOMS, "Bottoms", Icons.Default.DryCleaning),
        Triple(ClothingCategory.FOOTWEAR, "Shoes", Icons.Default.DirectionsWalk),
        Triple(ClothingCategory.OUTERWEAR, "Jackets", Icons.Default.Layers),
        Triple(ClothingCategory.ACCESSORIES, "Watches", Icons.Default.Watch),
        Triple(ClothingCategory.SOCKS, "Socks", Icons.Default.Spa),
        Triple(ClothingCategory.FORMAL_ACCESSORIES, "Ties", Icons.Default.Stars)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Spacing.base),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { (cat, label, icon) ->
            CategoryPill(
                label = label,
                icon = icon,
                onClick = { onCategoryClick(cat) }
            )
        }
    }
}

@Composable
fun CategoryPill(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(0.75.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag("category_pill_${label.lowercase()}"),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
