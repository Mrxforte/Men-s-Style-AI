package com.example.core.model

enum class ClothingCategory(val displayName: String, val iconName: String) {
    TOPS("Tops", "checkroom"),
    BOTTOMS("Bottoms", "dry_cleaning"),
    FOOTWEAR("Footwear", "directions_walk"),
    OUTERWEAR("Outerwear", "layers"),
    ACCESSORIES("Accessories", "watch"),
    SOCKS("Socks", "spa"),
    FORMAL_ACCESSORIES("Ties & Formal", "stars")
}

enum class FormalityLevel(val label: String) {
    CASUAL("Casual"),
    SMART_CASUAL("Smart Casual"),
    BUSINESS_CASUAL("Business Casual"),
    FORMAL("Formal Business / Black Tie")
}

enum class Season(val label: String) {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter"),
    ALL_SEASON("All Season")
}

data class ClothingItem(
    val id: String,
    val name: String,
    val category: ClothingCategory,
    val subcategory: String,
    val colorHex: String,
    val colorName: String,
    val material: String,
    val formality: FormalityLevel,
    val season: Season,
    val styleTags: List<String>,
    val description: String,
    val imageUrl: String = "",
    val isFavorite: Boolean = false
)
