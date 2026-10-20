package com.example.core.model

data class StylistConsultationRequest(
    val occasion: String,
    val weather: String,
    val preferredStyle: String,
    val preferredColors: List<String>,
    val ownedGarmentTypes: List<String>,
    val budgetLevel: String
)

data class StylistAdvice(
    val consultationSummary: String,
    val colorStrategy: String,
    val fabricAndLayeringTip: String,
    val footwearSelectionRationale: String,
    val groomingAndFinishingTouch: String,
    val recommendedOutfits: List<Outfit>
)
