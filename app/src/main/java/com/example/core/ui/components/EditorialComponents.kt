package com.example.core.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.core.model.Outfit
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.Spacing

fun parseHexColor(hex: String, fallback: Color = Color.Gray): Color {
    return try {
        Color(hex.toColorInt())
    } catch (e: Exception) {
        fallback
    }
}

object SharesheetHelper {
    fun shareOutfit(context: Context, outfit: Outfit) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                """
                👔 Men's Style AI — Outfit Formula:
                "${outfit.title}" (${outfit.styleCategory} • ${outfit.occasion})

                • Top: ${outfit.top.name} (${outfit.top.colorName})
                • Bottom: ${outfit.bottom.name} (${outfit.bottom.colorName})
                • Footwear: ${outfit.shoes.name} (${outfit.shoes.colorName})
                ${outfit.outerwear?.let { "• Layer: ${it.name} (${it.colorName})\n" } ?: ""}${outfit.belt?.let { "• Belt: ${it.name}\n" } ?: ""}${outfit.watch?.let { "• Timepiece: ${it.name}\n" } ?: ""}
                Styling Advice:
                ${outfit.stylingNotes}

                Shared via Men's Style AI
                """.trimIndent()
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Outfit Formula")
        context.startActivity(shareIntent)
    }
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (actionLabel != null && onActionClick != null) {
            Text(
                text = actionLabel,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onActionClick() }
                    .padding(horizontal = Spacing.xs, vertical = Spacing.xxs)
                    .testTag("section_action_${title.lowercase().replace(" ", "_")}")
            )
        }
    }
}

@Composable
fun StyleBadge(
    text: String,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = false
) {
    val bg = if (isPrimary) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .border(
                width = 0.5.dp,
                color = if (isPrimary) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                letterSpacing = 0.8.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = textColor
        )
    }
}

@Composable
fun ColorSwatchPill(
    colorHex: String,
    modifier: Modifier = Modifier,
    size: Dp = 18.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(parseHexColor(colorHex))
            .border(0.75.dp, borderColor, CircleShape)
    )
}

@Composable
fun FormalityRating(
    score: Int,
    modifier: Modifier = Modifier,
    maxScore: Int = 5
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(maxScore) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (index < score) GoldChampagne else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
                modifier = Modifier.size(13.dp)
            )
        }
    }
}
