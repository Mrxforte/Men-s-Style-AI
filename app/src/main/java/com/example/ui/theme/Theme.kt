package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GoldChampagne,
    onPrimary = CharcoalPure,
    primaryContainer = NavyCharcoal,
    onPrimaryContainer = GoldChampagne,
    secondary = BronzeGold,
    onSecondary = JetBlack,
    secondaryContainer = NavyLight,
    onSecondaryContainer = StoneLight,
    tertiary = CamelBeige,
    onTertiary = CharcoalPure,
    background = NavyDeep,
    onBackground = CreamIvory,
    surface = SurfaceCardDark,
    onSurface = CreamIvory,
    surfaceVariant = SurfaceElevatedDark,
    onSurfaceVariant = StoneMuted,
    outline = BorderSubtleDark,
    outlineVariant = NavyLight
)

private val LightColorScheme = lightColorScheme(
    primary = NavyDeep,
    onPrimary = CreamIvory,
    primaryContainer = StoneLight,
    onPrimaryContainer = NavyDeep,
    secondary = BronzeGold,
    onSecondary = Color.White,
    secondaryContainer = SandLight,
    onSecondaryContainer = NavyDeep,
    tertiary = WarmCognac,
    onTertiary = Color.White,
    background = CreamIvory,
    onBackground = JetBlack,
    surface = SurfaceCardLight,
    onSurface = JetBlack,
    surfaceVariant = CreamSurface,
    onSurfaceVariant = StoneDark,
    outline = BorderSubtleLight,
    outlineVariant = StoneLight
)

@Composable
fun MensStyleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
