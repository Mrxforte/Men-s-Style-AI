# Men's Style AI — Android Application

A production-grade, editorial men's fashion and style guidance application built with Kotlin, Jetpack Compose, Material 3, Clean Architecture, and MVVM.

## Architecture & Tech Stack

- **Architecture:** Clean Architecture (Presentation, Domain, Data) with MVVM pattern
- **UI Framework:** Jetpack Compose with Material 3 (M3)
- **Design Aesthetic:** High-end menswear editorial palette (Deep Navy, Midnight Charcoal, Warm Ivory, Stone Grey, Muted Olive, Camel Tan, Champagne Gold accents) with serif display typography and consistent 8dp spacing
- **Persistence:** Room Database (`AppDatabase`, `StyleDao`) for persisting saved custom outfits, bookmarked wardrobe pieces, and style preferences
- **State Management:** Kotlin Coroutines & StateFlow with `collectAsStateWithLifecycle`
- **Navigation:** Material 3 Bottom Navigation Bar with 5 core departments and sub-route switching with hardware `BackHandler` support
- **Sharing:** Native Android Sharesheet (`Intent.ACTION_SEND`) for exporting full outfit formulas and styling notes

## Implemented Features

1. **Discover / Editorial Home Screen:**
   - Sartorial hero banner and welcome section with quick AI Stylist consultation shortcut
   - Quick-access horizontal carousel for Wardrobe departments (Tops, Bottoms, Shoes, Outerwear, Watches, Socks, Ties)
   - Featured Outfit of the Day with full garment breakdown and color swatches
   - Trending Color Combinations showcase
   - Men's Posture & Photography Pose spotlight
   - Curated Editorial Ensembles feed with instant bookmarking

2. **Outfit Combination Engine & Catalog:**
   - **Curated Catalog:** Browse pre-assembled classic and contemporary menswear outfits with real-time text search, occasion filter (Office, Date Night, Weekend, Wedding Guest, Evening), and aesthetic filter (Old Money, Quiet Luxury, Smart Casual, Minimalist, Streetwear)
   - **Outfit Combination Engine:** Deterministic, explainable local outfit generation based on menswear color harmony rules and formality alignment
   - **Slot Locking:** Lock individual clothing pieces (Top, Bottom, Shoes, Outerwear) while regenerating the rest of the outfit
   - **Color Exclusions:** Exclude specific unwanted colors (Black, Navy, Olive, Camel, etc.) from generation
   - **Save & Share:** Save custom generated outfits to local Room database and share formulas via Android Sharesheet

3. **Wardrobe Catalog & Filtering:**
   - 50+ curated menswear pieces across all major categories (Oxford shirts, Linen shirts, Knit polos, Sweaters, Flannel trousers, Raw selvedge denim, Chinos, Belgian loafers, Minimal white sneakers, Chelsea boots, Hopsack blazers, Trench coats, Leather jackets, Silk grenadine ties, etc.)
   - Department filtering: Tops, Bottoms, Footwear, Outerwear, Accessories, Socks, Ties & Formal
   - Formality filtering: Casual, Smart Casual, Business Casual, Formal
   - Real-time search by garment name, material, color, or subcategory
   - Item Detail modal dialog with fabric composition, formality score, seasonal suitability, and styling descriptions
   - Bookmarking / favorite persistence

4. **Color Harmony Explorer:**
   - Dedicated color matching engine inspired by editorial menswear palettes
   - Real hex color swatches (Navy & Grey, Cream & Navy, Olive & Beige, Charcoal & Black, Sky Blue & White, Camel & Black, Deep Emerald & Khaki, etc.)
   - Filter combinations by base color
   - Detailed garment allocation: Top color, Bottom color, Footwear color, Belt & Socks advice, and formality ratings

5. **Men's Posture & Photography Pose Guide:**
   - Practical, respectful guidance for confident posture in photos and everyday life
   - Categorized by pose type: Standing (S-Curve), Walking (Candid Stride), Leaning (Wall Lean), Seated (Café Pose), Portrait (Watch & Cuff interaction), Formal (Executive Buttoning)
   - Detailed step-by-step posture cues, arm and hand positioning, foot placement, shoulder and head alignment, camera angle suggestions, and common mistakes to avoid

6. **Style Guides:**
   - Timeless aesthetic profiles: Old Money / Ivy League, Quiet Luxury, Smart Casual, Modern Minimalist, Elevated Streetwear
   - Core philosophy, signature garments, recommended color pairings, footwear picks, accessory guidelines, and common mistakes to avoid

7. **Saved / Favorites:**
   - Persistent Room database tracking custom generated outfits and favorite wardrobe pieces
   - Two-tab layout: Saved Outfits vs. Favorite Pieces
   - Survives app restarts and device offline usage

8. **AI Stylist Consultation (with Local Fallback):**
   - Context questionnaire: Event/Occasion, Weather/Climate, Desired Aesthetic, Budget
   - Generates structured sartorial advice (Consultation Summary, Color Strategy, Fabric & Layering Tip, Footwear Selection Rationale, Grooming & Details)
   - Generates bespoke outfit recommendations matching the user's constraints

## How to Build & Run

```bash
# Build the project
gradle assembleDebug

# Run unit and Robolectric tests
gradle :app:testDebugUnitTest
```
