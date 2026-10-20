package com.example.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val id: String,
    val itemType: String, // "OUTFIT", "CLOTHING_ITEM", "COLOR_COMBO", "POSE"
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_outfits")
data class SavedOutfitEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val styleCategory: String,
    val occasion: String,
    val season: String,
    val topId: String,
    val bottomId: String,
    val shoesId: String,
    val outerwearId: String? = null,
    val beltId: String? = null,
    val watchId: String? = null,
    val socksId: String? = null,
    val tieId: String? = null,
    val accessoryId: String? = null,
    val stylingNotes: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey
    val key: String,
    val value: String
)
