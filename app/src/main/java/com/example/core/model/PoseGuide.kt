package com.example.core.model

data class PoseGuide(
    val id: String,
    val title: String,
    val category: String, // "Standing", "Walking", "Leaning", "Seated", "Portrait", "Editorial"
    val postureCues: List<String>,
    val armAndHandPosition: String,
    val footAndLegPlacement: String,
    val shoulderAndHeadAlignment: String,
    val cameraAngleAndFraming: String,
    val mistakesToAvoid: List<String>,
    val complementaryOutfits: List<String>,
    val confidenceNote: String
)
