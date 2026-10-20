package com.example

import com.example.core.data.SeedData
import com.example.core.domain.LockedOutfitSlots
import com.example.core.domain.OutfitCombinationEngine
import com.example.core.model.ClothingCategory
import com.example.core.model.Season
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class OutfitCombinationEngineTest {

    @Test
    fun generateOutfit_createsValidOutfitWithAllCorePieces() {
        val outfit = OutfitCombinationEngine.generateOutfit(
            styleCategory = "Smart Casual",
            occasion = "Office",
            season = Season.ALL_SEASON
        )

        assertNotNull(outfit)
        assertEquals(ClothingCategory.TOPS, outfit.top.category)
        assertEquals(ClothingCategory.BOTTOMS, outfit.bottom.category)
        assertEquals(ClothingCategory.FOOTWEAR, outfit.shoes.category)
        assertTrue(outfit.colorPalette.isNotEmpty())
        assertTrue(outfit.stylingNotes.isNotBlank())
        assertTrue(outfit.formalityScore in 1..5)
    }

    @Test
    fun generateOutfit_respectsLockedTopSlot() {
        val lockedTop = SeedData.findItem("top_knit_polo_cream")
        val lockedSlots = LockedOutfitSlots(top = lockedTop)

        val outfit = OutfitCombinationEngine.generateOutfit(
            lockedSlots = lockedSlots
        )

        assertEquals("top_knit_polo_cream", outfit.top.id)
    }

    @Test
    fun generateOutfit_respectsLockedBottomAndShoesSlots() {
        val lockedBottom = SeedData.findItem("bottom_trouser_grey")
        val lockedShoes = SeedData.findItem("shoes_loafer_brown")
        val lockedSlots = LockedOutfitSlots(bottom = lockedBottom, shoes = lockedShoes)

        val outfit = OutfitCombinationEngine.generateOutfit(
            lockedSlots = lockedSlots
        )

        assertEquals("bottom_trouser_grey", outfit.bottom.id)
        assertEquals("shoes_loafer_brown", outfit.shoes.id)
    }

    @Test
    fun leatherMatchingRule_blackShoesReceiveBlackBelt() {
        val blackShoes = SeedData.findItem("shoes_oxford_black")
        val belt = OutfitCombinationEngine.getMatchingBelt(blackShoes)

        assertNotNull(belt)
        assertEquals("acc_belt_black", belt?.id)
    }

    @Test
    fun leatherMatchingRule_brownShoesReceiveBrownBelt() {
        val brownShoes = SeedData.findItem("shoes_loafer_brown")
        val belt = OutfitCombinationEngine.getMatchingBelt(brownShoes)

        assertNotNull(belt)
        assertEquals("acc_belt_brown", belt?.id)
    }

    @Test
    fun colorExclusion_excludesSpecificColor() {
        val blackColorHex = "#18181B"
        val outfit = OutfitCombinationEngine.generateOutfit(
            excludedColorHexes = setOf(blackColorHex)
        )

        assertTrue(outfit.top.colorHex != blackColorHex)
    }
}
