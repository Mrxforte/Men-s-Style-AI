package com.example.core.model

data class ColorCombination(
    val id: String,
    val name: String,
    val primaryHex: String,
    val secondaryHex: String,
    val accentHex: String,
    val topColorName: String,
    val bottomColorName: String,
    val footwearColorName: String,
    val beltAndSocksAdvice: String,
    val formalityLevel: FormalityLevel,
    val bestOccasions: List<String>,
    val seasonalSuitability: List<Season>,
    val stylingRationale: String
)
