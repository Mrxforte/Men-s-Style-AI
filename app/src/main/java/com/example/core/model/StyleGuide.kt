package com.example.core.model

data class StyleGuide(
    val id: String,
    val title: String,
    val tagLine: String,
    val aestheticPhilosophy: String,
    val keySartorialRule: String,
    val coreGarments: List<String>,
    val recommendedColorPairs: List<Pair<String, String>>, // Color name to Hex
    val footwearRecommendations: List<String>,
    val accessoryGuidelines: List<String>,
    val commonMistakesToAvoid: List<String>,
    val primeOccasions: List<String>
)
