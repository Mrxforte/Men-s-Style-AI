package com.example.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StyleDao {

    // Favorites
    @Query("SELECT * FROM favorites ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE itemType = :type ORDER BY timestamp DESC")
    fun getFavoritesByType(type: String): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun removeFavorite(id: String)

    // Saved Outfits
    @Query("SELECT * FROM saved_outfits ORDER BY createdAt DESC")
    fun getAllSavedOutfits(): Flow<List<SavedOutfitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedOutfit(outfit: SavedOutfitEntity)

    @Query("DELETE FROM saved_outfits WHERE id = :id")
    suspend fun deleteSavedOutfit(id: String)

    @Query("SELECT * FROM saved_outfits WHERE id = :id LIMIT 1")
    suspend fun getSavedOutfitById(id: String): SavedOutfitEntity?

    // Preferences
    @Query("SELECT value FROM user_preferences WHERE `key` = :key LIMIT 1")
    suspend fun getPreference(key: String): String?

    @Query("SELECT value FROM user_preferences WHERE `key` = :key LIMIT 1")
    fun observePreference(key: String): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPreference(preference: UserPreferenceEntity)
}
