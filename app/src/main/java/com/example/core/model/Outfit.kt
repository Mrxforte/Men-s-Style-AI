package com.example.core.model

data class Outfit(
    val id: String,
    val title: String,
    val styleCategory: String, // e.g., "Old Money", "Quiet Luxury", "Smart Casual", "Minimalist"
    val occasion: String, // e.g., "Office", "Date Night", "Weekend", "Wedding Guest"
    val season: Season,
    val top: ClothingItem,
    val bottom: ClothingItem,
    val shoes: ClothingItem,
    val outerwear: ClothingItem? = null,
    val belt: ClothingItem? = null,
    val watch: ClothingItem? = null,
    val socks: ClothingItem? = null,
    val tie: ClothingItem? = null,
    val accessory: ClothingItem? = null,
    val colorPalette: List<String>, // Hex strings
    val stylingNotes: String,
    val fitGuidance: String,
    val formalityScore: Int = 3, // 1 (ultra-casual) to 5 (black tie)
    val isFavorite: Boolean = false
) {
    val allPieces: List<ClothingItem>
        get() = listOfNotNull(top, bottom, shoes, outerwear, belt, watch, socks, tie, accessory)
}
