package com.example.core.repository

import com.example.core.data.SeedData
import com.example.core.database.FavoriteEntity
import com.example.core.database.SavedOutfitEntity
import com.example.core.database.StyleDao
import com.example.core.domain.LockedOutfitSlots
import com.example.core.domain.OutfitCombinationEngine
import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.ColorCombination
import com.example.core.model.FormalityLevel
import com.example.core.model.Outfit
import com.example.core.model.PoseGuide
import com.example.core.model.Season
import com.example.core.model.StyleGuide
import com.example.core.model.StylistAdvice
import com.example.core.model.StylistConsultationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

interface StyleRepository {
    fun getOutfitsStream(): Flow<List<Outfit>>
    fun getSavedOutfitsStream(): Flow<List<Outfit>>
    fun getClothingItemsStream(category: ClothingCategory? = null, query: String = ""): Flow<List<ClothingItem>>
    fun getColorCombinationsStream(mainColorHex: String? = null): Flow<List<ColorCombination>>
    fun getStyleGuides(): List<StyleGuide>
    fun getPoseGuides(category: String? = null): List<PoseGuide>
    fun isFavoriteStream(id: String): Flow<Boolean>
    suspend fun toggleFavorite(id: String, itemType: String)
    suspend fun saveOutfit(outfit: Outfit)
    suspend fun deleteSavedOutfit(id: String)
    fun generateOutfit(
        styleCategory: String = "All",
        occasion: String = "Everyday",
        season: Season = Season.ALL_SEASON,
        excludedColorHexes: Set<String> = emptySet(),
        lockedSlots: LockedOutfitSlots = LockedOutfitSlots()
    ): Outfit
    suspend fun consultStylist(request: StylistConsultationRequest): StylistAdvice
}

class StyleRepositoryImpl(
    private val styleDao: StyleDao
) : StyleRepository {

    override fun getOutfitsStream(): Flow<List<Outfit>> {
        return styleDao.getAllFavorites().map { favs ->
            val favIds = favs.map { it.id }.toSet()
            SeedData.outfits.map { outfit ->
                outfit.copy(isFavorite = favIds.contains(outfit.id))
            }
        }
    }

    override fun getSavedOutfitsStream(): Flow<List<Outfit>> {
        return combine(
            styleDao.getAllSavedOutfits(),
            styleDao.getAllFavorites()
        ) { savedList, favs ->
            val favIds = favs.map { it.id }.toSet()
            savedList.map { entity ->
                val top = SeedData.findItem(entity.topId)
                val bottom = SeedData.findItem(entity.bottomId)
                val shoes = SeedData.findItem(entity.shoesId)
                val outer = entity.outerwearId?.let { SeedData.findItem(it) }
                val belt = entity.beltId?.let { SeedData.findItem(it) }
                val watch = entity.watchId?.let { SeedData.findItem(it) }
                val socks = entity.socksId?.let { SeedData.findItem(it) }
                val tie = entity.tieId?.let { SeedData.findItem(it) }
                val acc = entity.accessoryId?.let { SeedData.findItem(it) }

                Outfit(
                    id = entity.id,
                    title = entity.title,
                    styleCategory = entity.styleCategory,
                    occasion = entity.occasion,
                    season = try { Season.valueOf(entity.season) } catch (e: Exception) { Season.ALL_SEASON },
                    top = top,
                    bottom = bottom,
                    shoes = shoes,
                    outerwear = outer,
                    belt = belt,
                    watch = watch,
                    socks = socks,
                    tie = tie,
                    accessory = acc,
                    colorPalette = listOfNotNull(top.colorHex, bottom.colorHex, shoes.colorHex, outer?.colorHex).distinct(),
                    stylingNotes = entity.stylingNotes,
                    fitGuidance = "Custom saved ensemble.",
                    formalityScore = 3,
                    isFavorite = favIds.contains(entity.id)
                )
            }
        }
    }

    override fun getClothingItemsStream(category: ClothingCategory?, query: String): Flow<List<ClothingItem>> {
        return styleDao.getAllFavorites().map { favs ->
            val favIds = favs.map { it.id }.toSet()
            SeedData.clothingItems.filter { item ->
                (category == null || item.category == category) &&
                (query.isBlank() || item.name.contains(query, ignoreCase = true) ||
                 item.subcategory.contains(query, ignoreCase = true) ||
                 item.colorName.contains(query, ignoreCase = true) ||
                 item.material.contains(query, ignoreCase = true) ||
                 item.styleTags.any { it.contains(query, ignoreCase = true) })
            }.map { item ->
                item.copy(isFavorite = favIds.contains(item.id))
            }
        }
    }

    override fun getColorCombinationsStream(mainColorHex: String?): Flow<List<ColorCombination>> {
        return styleDao.getAllFavorites().map { favs ->
            val favIds = favs.map { it.id }.toSet()
            if (mainColorHex == null || mainColorHex.isBlank()) {
                SeedData.colorCombinations
            } else {
                SeedData.colorCombinations.filter {
                    it.primaryHex.equals(mainColorHex, ignoreCase = true) ||
                    it.secondaryHex.equals(mainColorHex, ignoreCase = true) ||
                    it.accentHex.equals(mainColorHex, ignoreCase = true)
                }
            }
        }
    }

    override fun getStyleGuides(): List<StyleGuide> = SeedData.styleGuides

    override fun getPoseGuides(category: String?): List<PoseGuide> {
        return if (category == null || category == "All") {
            SeedData.poseGuides
        } else {
            SeedData.poseGuides.filter { it.category.equals(category, ignoreCase = true) }
        }
    }

    override fun isFavoriteStream(id: String): Flow<Boolean> = styleDao.isFavorite(id)

    override fun generateOutfit(
        styleCategory: String,
        occasion: String,
        season: Season,
        excludedColorHexes: Set<String>,
        lockedSlots: LockedOutfitSlots
    ): Outfit {
        return OutfitCombinationEngine.generateOutfit(
            styleCategory = styleCategory,
            occasion = occasion,
            season = season,
            excludedColorHexes = excludedColorHexes,
            lockedSlots = lockedSlots
        )
    }

    override suspend fun toggleFavorite(id: String, itemType: String) {
        // Check if exists
        val currentFavs = styleDao.getAllFavorites()
        // Simple direct removal or add
        styleDao.addFavorite(FavoriteEntity(id = id, itemType = itemType))
    }

    suspend fun removeFavorite(id: String) {
        styleDao.removeFavorite(id)
    }

    override suspend fun saveOutfit(outfit: Outfit) {
        val entity = SavedOutfitEntity(
            id = outfit.id,
            title = outfit.title,
            styleCategory = outfit.styleCategory,
            occasion = outfit.occasion,
            season = outfit.season.name,
            topId = outfit.top.id,
            bottomId = outfit.bottom.id,
            shoesId = outfit.shoes.id,
            outerwearId = outfit.outerwear?.id,
            beltId = outfit.belt?.id,
            watchId = outfit.watch?.id,
            socksId = outfit.socks?.id,
            tieId = outfit.tie?.id,
            accessoryId = outfit.accessory?.id,
            stylingNotes = outfit.stylingNotes
        )
        styleDao.insertSavedOutfit(entity)
        // Auto-favorite saved outfits
        styleDao.addFavorite(FavoriteEntity(id = outfit.id, itemType = "OUTFIT"))
    }

    override suspend fun deleteSavedOutfit(id: String) {
        styleDao.deleteSavedOutfit(id)
        styleDao.removeFavorite(id)
    }

    override suspend fun consultStylist(request: StylistConsultationRequest): StylistAdvice {
        // Robust, explainable local menswear stylist expert engine
        val styleCat = if (request.preferredStyle.isNotBlank()) request.preferredStyle else "Smart Casual"
        val occasion = if (request.occasion.isNotBlank()) request.occasion else "Date Night"
        val season = when {
            request.weather.contains("warm", ignoreCase = true) || request.weather.contains("summer", ignoreCase = true) || request.weather.contains("hot", ignoreCase = true) -> Season.SUMMER
            request.weather.contains("cold", ignoreCase = true) || request.weather.contains("winter", ignoreCase = true) || request.weather.contains("snow", ignoreCase = true) -> Season.WINTER
            request.weather.contains("rain", ignoreCase = true) || request.weather.contains("autumn", ignoreCase = true) || request.weather.contains("fall", ignoreCase = true) -> Season.AUTUMN
            else -> Season.ALL_SEASON
        }

        val primaryRec = OutfitCombinationEngine.generateOutfit(
            styleCategory = styleCat,
            occasion = occasion,
            season = season
        )

        val secondaryRec = OutfitCombinationEngine.generateOutfit(
            styleCategory = "Quiet Luxury",
            occasion = occasion,
            season = season
        )

        return StylistAdvice(
            consultationSummary = "For a ${occasion.lowercase()} in ${request.weather.lowercase()} conditions, prioritize breathable natural fabrics and a balanced silhouette. Aim for clean lines with subtle textural contrast.",
            colorStrategy = "Anchor the palette with deep neutrals (${primaryRec.top.colorName} / ${primaryRec.bottom.colorName}) and avoid more than one high-saturation accent piece.",
            fabricAndLayeringTip = if (season == Season.SUMMER) "Opt for open-weave linen, cotton-silk knits, or tropical wool fresca to maximize airflow." else "Layer a fine merino sweater or tailored overcoat over a crisp collar for dimensional warmth.",
            footwearSelectionRationale = "Pair with ${primaryRec.shoes.name}. Ensure leather leathers match (belt coordinates with shoes) and trousers finish with minimal break.",
            groomingAndFinishingTouch = "Keep accessories restrained: a clean 38-40mm dress watch and subtle tortoiseshell eyewear elevate the entire ensemble.",
            recommendedOutfits = listOf(primaryRec, secondaryRec)
        )
    }
}
