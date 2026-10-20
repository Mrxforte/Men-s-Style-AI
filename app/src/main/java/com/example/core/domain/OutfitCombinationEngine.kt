package com.example.core.domain

import com.example.core.data.SeedData
import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.FormalityLevel
import com.example.core.model.Outfit
import com.example.core.model.Season
import java.util.UUID

data class LockedOutfitSlots(
    val top: ClothingItem? = null,
    val bottom: ClothingItem? = null,
    val shoes: ClothingItem? = null,
    val outerwear: ClothingItem? = null,
    val belt: ClothingItem? = null,
    val watch: ClothingItem? = null,
    val socks: ClothingItem? = null,
    val tie: ClothingItem? = null,
    val isOuterwearLockedEmpty: Boolean = false,
    val isTieLockedEmpty: Boolean = false
)

object OutfitCombinationEngine {

    // Color harmony rule check
    fun areColorsHarmonious(color1Hex: String, color2Hex: String): Boolean {
        // Universal neutrals match everything
        val neutrals = setOf("#FFFFFF", "#F8FAFC", "#FAF8F5", "#F4EFEA", "#E2E8F0", "#CBD5E1", "#64748B", "#475569", "#334155", "#1E293B", "#18181B", "#0F172A", "#09090B")
        if (neutrals.contains(color1Hex) || neutrals.contains(color2Hex)) return true

        // Earth tones harmony: olive + beige, camel + brown, sage + khaki
        val earthTones = setOf("#4A5540", "#5B6E4E", "#D4B996", "#E6D5B8", "#C4A482", "#8B4513", "#78350F")
        if (earthTones.contains(color1Hex) && earthTones.contains(color2Hex)) return true

        // Blues + Earth / Neutrals
        val blues = setOf("#93C5FD", "#0F172A", "#1E293B", "#476072")
        if (blues.contains(color1Hex) && earthTones.contains(color2Hex)) return true
        if (blues.contains(color2Hex) && earthTones.contains(color1Hex)) return true

        return true
    }

    // Leathers matching rule: Brown shoes need brown belt; black shoes need black belt
    fun getMatchingBelt(shoes: ClothingItem): ClothingItem? {
        val isBlackLeather = shoes.colorHex in setOf("#09090B", "#18181B")
        return if (isBlackLeather) {
            SeedData.clothingItems.find { it.id == "acc_belt_black" }
        } else {
            SeedData.clothingItems.find { it.id == "acc_belt_brown" }
        }
    }

    fun generateOutfit(
        styleCategory: String = "All",
        occasion: String = "Everyday",
        season: Season = Season.ALL_SEASON,
        excludedColorHexes: Set<String> = emptySet(),
        lockedSlots: LockedOutfitSlots = LockedOutfitSlots()
    ): Outfit {
        val allItems = SeedData.clothingItems

        // Filter by exclusions
        fun filterItems(category: ClothingCategory): List<ClothingItem> {
            return allItems.filter { item ->
                item.category == category &&
                !excludedColorHexes.contains(item.colorHex) &&
                (season == Season.ALL_SEASON || item.season == Season.ALL_SEASON || item.season == season) &&
                (styleCategory == "All" || item.styleTags.any { it.equals(styleCategory, ignoreCase = true) })
            }.ifEmpty {
                // Fallback to all items in category if filters are too restrictive
                allItems.filter { it.category == category && !excludedColorHexes.contains(it.colorHex) }
            }.ifEmpty {
                allItems.filter { it.category == category }
            }
        }

        // 1. Resolve Top
        val top = lockedSlots.top ?: filterItems(ClothingCategory.TOPS).shuffled().first()

        // 2. Resolve Bottom compatible with Top formality and color
        val compatibleBottoms = filterItems(ClothingCategory.BOTTOMS).filter { bottom ->
            areColorsHarmonious(top.colorHex, bottom.colorHex) &&
            Math.abs(top.formality.ordinal - bottom.formality.ordinal) <= 1
        }.ifEmpty { filterItems(ClothingCategory.BOTTOMS) }
        val bottom = lockedSlots.bottom ?: compatibleBottoms.shuffled().first()

        // 3. Resolve Shoes compatible with Bottom & Top
        val targetFormality = maxOf(top.formality.ordinal, bottom.formality.ordinal)
        val compatibleShoes = filterItems(ClothingCategory.FOOTWEAR).filter { shoe ->
            Math.abs(shoe.formality.ordinal - targetFormality) <= 1 &&
            areColorsHarmonious(bottom.colorHex, shoe.colorHex)
        }.ifEmpty { filterItems(ClothingCategory.FOOTWEAR) }
        val shoes = lockedSlots.shoes ?: compatibleShoes.shuffled().first()

        // 4. Resolve Outerwear
        val outerwear: ClothingItem? = if (lockedSlots.isOuterwearLockedEmpty) {
            null
        } else if (lockedSlots.outerwear != null) {
            lockedSlots.outerwear
        } else {
            val outerCandidates = filterItems(ClothingCategory.OUTERWEAR).filter { outer ->
                areColorsHarmonious(outer.colorHex, top.colorHex) &&
                areColorsHarmonious(outer.colorHex, bottom.colorHex) &&
                Math.abs(outer.formality.ordinal - targetFormality) <= 1
            }
            if (outerCandidates.isNotEmpty() && season != Season.SUMMER) outerCandidates.shuffled().firstOrNull() else null
        }

        // 5. Resolve Belt (matches shoes leather)
        val belt = lockedSlots.belt ?: getMatchingBelt(shoes)

        // 6. Resolve Watch
        val watch = lockedSlots.watch ?: allItems.find { it.category == ClothingCategory.ACCESSORIES && it.subcategory == "Watches" }

        // 7. Resolve Socks (Loafers in Summer -> no show, else dress/ribbed)
        val socks = lockedSlots.socks ?: if (shoes.subcategory == "Loafers" || season == Season.SUMMER) {
            allItems.find { it.id == "sock_noshow_invisible" }
        } else {
            allItems.find { it.id == "sock_dress_charcoal" }
        }

        // 8. Resolve Tie (only if formal or high smart casual with collared top)
        val tie: ClothingItem? = if (lockedSlots.isTieLockedEmpty) {
            null
        } else if (lockedSlots.tie != null) {
            lockedSlots.tie
        } else if (targetFormality >= FormalityLevel.BUSINESS_CASUAL.ordinal && top.subcategory.contains("shirt", ignoreCase = true)) {
            allItems.filter { it.category == ClothingCategory.FORMAL_ACCESSORIES && it.subcategory.contains("tie", ignoreCase = true) }.shuffled().firstOrNull()
        } else null

        // 9. Generate Styling Rationale
        val stylingNotes = buildString {
            append("A refined pairing anchored by ${top.colorName} ${top.name} and ${bottom.colorName} ${bottom.name}. ")
            if (outerwear != null) {
                append("Layered with ${outerwear.name} to introduce depth. ")
            }
            append("Grounded by ${shoes.name} and coordinated ${belt?.name ?: "accents"}.")
        }

        val fitGuidance = "Ensure balance: if the ${top.subcategory} is relaxed, ensure ${bottom.name} has a structured drape. Shoes should be clean and well-maintained."

        val palette = listOfNotNull(top.colorHex, bottom.colorHex, shoes.colorHex, outerwear?.colorHex).distinct()

        val calculatedFormality = ((top.formality.ordinal + bottom.formality.ordinal + shoes.formality.ordinal) / 3) + 1

        return Outfit(
            id = "generated_${UUID.randomUUID().toString().take(8)}",
            title = "${top.colorName} & ${bottom.colorName} Coordination",
            styleCategory = if (styleCategory == "All") "Curated" else styleCategory,
            occasion = occasion,
            season = season,
            top = top,
            bottom = bottom,
            shoes = shoes,
            outerwear = outerwear,
            belt = belt,
            watch = watch,
            socks = socks,
            tie = tie,
            accessory = null,
            colorPalette = palette,
            stylingNotes = stylingNotes,
            fitGuidance = fitGuidance,
            formalityScore = calculatedFormality.coerceIn(1, 5)
        )
    }
}
