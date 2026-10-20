package com.example.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.core.model.Outfit
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.Spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OutfitCard(
    outfit: Outfit,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(0.75.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("outfit_card_${outfit.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(Spacing.base)) {
            // Header with Style, Occasion, and Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StyleBadge(text = outfit.styleCategory, isPrimary = true)
                    StyleBadge(text = outfit.occasion)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { SharesheetHelper.shareOutfit(context, outfit) },
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("share_outfit_${outfit.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Outfit",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("fav_outfit_${outfit.id}")
                    ) {
                        Icon(
                            imageVector = if (outfit.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (outfit.isFavorite) "Remove from Saved" else "Save Outfit",
                            tint = if (outfit.isFavorite) GoldChampagne else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.xs))

            // Title & Formality
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = outfit.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                FormalityRating(score = outfit.formalityScore)
            }

            Spacer(modifier = Modifier.height(Spacing.sm))

            // Palette Swatches
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PALETTE:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                outfit.colorPalette.forEach { hex ->
                    ColorSwatchPill(colorHex = hex, size = 16.dp)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.md))

            // Garment Pieces Breakdown
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
                OutfitPieceRow(role = "TOP", item = outfit.top.name, color = outfit.top.colorName, hex = outfit.top.colorHex)
                OutfitPieceRow(role = "BOTTOM", item = outfit.bottom.name, color = outfit.bottom.colorName, hex = outfit.bottom.colorHex)
                OutfitPieceRow(role = "SHOES", item = outfit.shoes.name, color = outfit.shoes.colorName, hex = outfit.shoes.colorHex)
                if (outfit.outerwear != null) {
                    OutfitPieceRow(role = "LAYER", item = outfit.outerwear.name, color = outfit.outerwear.colorName, hex = outfit.outerwear.colorHex)
                }
                if (outfit.belt != null) {
                    OutfitPieceRow(role = "BELT", item = outfit.belt.name, color = outfit.belt.colorName, hex = outfit.belt.colorHex)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.sm))

            // Styling notes quote
            Text(
                text = "\"${outfit.stylingNotes}\"",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
        }
    }
}

@Composable
fun OutfitPieceRow(
    role: String,
    item: String,
    color: String,
    hex: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = role,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(55.dp)
        )
        ColorSwatchPill(colorHex = hex, size = 10.dp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "$item • $color",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}
