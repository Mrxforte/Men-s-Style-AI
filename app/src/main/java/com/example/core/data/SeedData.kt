package com.example.core.data

import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.ColorCombination
import com.example.core.model.FormalityLevel
import com.example.core.model.Outfit
import com.example.core.model.PoseGuide
import com.example.core.model.Season
import com.example.core.model.StyleGuide

object SeedData {

    // --- CLOTHING ITEMS ---
    val clothingItems: List<ClothingItem> = listOf(
        // TOPS
        ClothingItem(
            id = "top_oxford_white",
            name = "Classic Oxford Cloth Button-Down",
            category = ClothingCategory.TOPS,
            subcategory = "Oxford Shirts",
            colorHex = "#FFFFFF",
            colorName = "Optic White",
            material = "100% Pima Cotton",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Classic", "Smart Casual", "Old Money"),
            description = "Substantial cotton basketweave with a generous collar roll. The quintessential foundational shirt for any modern wardrobe."
        ),
        ClothingItem(
            id = "top_oxford_lightblue",
            name = "Washed Sky Blue Oxford Shirt",
            category = ClothingCategory.TOPS,
            subcategory = "Oxford Shirts",
            colorHex = "#93C5FD",
            colorName = "Sky Blue",
            material = "Organic Cotton Oxford",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Preppy", "Smart Casual", "Office"),
            description = "Softly structured light blue button-down that pairs harmoniously with navy, stone, khaki, and charcoal."
        ),
        ClothingItem(
            id = "top_linen_cream",
            name = "Relaxed Camp-Collar Linen Shirt",
            category = ClothingCategory.TOPS,
            subcategory = "Linen Shirts",
            colorHex = "#F4EFEA",
            colorName = "Warm Sand",
            material = "100% French Linen",
            formality = FormalityLevel.CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Summer", "Old Money", "Resort"),
            description = "Airy, breathable summer staple featuring a laid-back Cuban collar and natural slub texture."
        ),
        ClothingItem(
            id = "top_knit_polo_navy",
            name = "Fine-Gauge Merino Knit Polo",
            category = ClothingCategory.TOPS,
            subcategory = "Polo Shirts",
            colorHex = "#0F172A",
            colorName = "Deep Midnight Navy",
            material = "Extra-Fine Merino Wool",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Quiet Luxury", "Minimalist", "Date Night"),
            description = "Silky drape with ribbed hem and cuffs. Effortlessly elevates casual denim or tailored trousers."
        ),
        ClothingItem(
            id = "top_knit_polo_cream",
            name = "Johnny-Collar Textured Knit Polo",
            category = ClothingCategory.TOPS,
            subcategory = "Polo Shirts",
            colorHex = "#FAF8F5",
            colorName = "Warm Ivory",
            material = "Cotton-Silk Blend",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.SPRING,
            styleTags = listOf("Old Money", "Summer", "Quiet Luxury"),
            description = "Buttonless placket polo offering an Italian Riviera aesthetic with structured sophistication."
        ),
        ClothingItem(
            id = "top_tee_heavy_white",
            name = "Heavyweight Boxy Supima Tee",
            category = ClothingCategory.TOPS,
            subcategory = "Basic T-shirts",
            colorHex = "#F8FAFC",
            colorName = "Pure White",
            material = "240 GSM Combed Cotton",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Minimalist", "Streetwear", "Everyday"),
            description = "Crisp neckline that never sags, dense drape, and clean structured silhouette."
        ),
        ClothingItem(
            id = "top_tee_black",
            name = "Mercerized Crewneck T-Shirt",
            category = ClothingCategory.TOPS,
            subcategory = "Basic T-shirts",
            colorHex = "#18181B",
            colorName = "Charcoal Black",
            material = "Mercerized Egyptian Cotton",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Minimalist", "Monochrome", "Date Night"),
            description = "Subtle sheen and deep colorfast saturation. Perfect worn solo or beneath tailored jackets."
        ),
        ClothingItem(
            id = "top_merino_sweater_camel",
            name = "Crewneck Merino Sweater",
            category = ClothingCategory.TOPS,
            subcategory = "Sweaters",
            colorHex = "#C4A482",
            colorName = "Camel Tan",
            material = "100% Merino Wool",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.AUTUMN,
            styleTags = listOf("Old Money", "Classic", "Office"),
            description = "Rich camel hue that flatters diverse skin tones and layers seamlessly over collared shirts."
        ),
        ClothingItem(
            id = "top_turtleneck_charcoal",
            name = "Fine Ribbed Rollneck Turtleneck",
            category = ClothingCategory.TOPS,
            subcategory = "Turtlenecks",
            colorHex = "#334155",
            colorName = "Charcoal Slate",
            material = "Cashmere & Silk Blend",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.WINTER,
            styleTags = listOf("Quiet Luxury", "Evening", "Minimalist"),
            description = "Sophisticated winter statement piece that instantly turns a suit into modern evening-wear."
        ),
        ClothingItem(
            id = "top_dress_shirt_twill",
            name = "Tailored French-Cuff Dress Shirt",
            category = ClothingCategory.TOPS,
            subcategory = "Formal dress shirts",
            colorHex = "#FFFFFF",
            colorName = "Brilliant White",
            material = "Two-Ply Egyptian Cotton Twill",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Black Tie", "Business"),
            description = "Semi-spread collar with removable brass stays and double cuffs for sterling cufflinks."
        ),
        ClothingItem(
            id = "top_hoodie_grey",
            name = "Loopback French Terry Hoodie",
            category = ClothingCategory.TOPS,
            subcategory = "Hoodies",
            colorHex = "#CBD5E1",
            colorName = "Heather Grey",
            material = "100% Heavyweight Cotton",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Athleisure", "Streetwear", "Weekend"),
            description = "Double-lined hood that holds its structure, ribbed side gussets, and vintage athletic drape."
        ),

        // BOTTOMS
        ClothingItem(
            id = "bottom_trouser_grey",
            name = "Pleated Flannel Trousers",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Formal trousers",
            colorHex = "#475569",
            colorName = "Mid Grey Flannel",
            material = "100% Wool Flannel",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.AUTUMN,
            styleTags = listOf("Classic", "Old Money", "Office"),
            description = "Single forward pleat, side tab adjusters, and a clean taper. The benchmark of tailored menswear."
        ),
        ClothingItem(
            id = "bottom_trouser_navy",
            name = "Tailored High-Twist Wool Trousers",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Formal trousers",
            colorHex = "#0F172A",
            colorName = "Midnight Navy",
            material = "High-Twist Fresco Wool",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Business", "Sharp"),
            description = "Wrinkle-resistant high-twist weave with sharp permanent center crease."
        ),
        ClothingItem(
            id = "bottom_chino_beige",
            name = "Straight-Fit Garment-Dyed Chinos",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Chinos",
            colorHex = "#D4B996",
            colorName = "Sand Beige",
            material = "98% Cotton, 2% Elastane",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Smart Casual", "Everyday", "Versatile"),
            description = "Versatile neutral tone with slight stretch for mobility. Sits naturally at the natural waist."
        ),
        ClothingItem(
            id = "bottom_chino_olive",
            name = "Relaxed Fatigue Trousers",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Chinos",
            colorHex = "#4A5540",
            colorName = "Military Olive",
            material = "Cotton Reverse Sateen",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Casual", "Streetwear", "Workwear"),
            description = "Authentic patch pockets, durable wash, and easy relaxed straight cut."
        ),
        ClothingItem(
            id = "bottom_denim_selvedge",
            name = "Raw Japanese Selvedge Denim",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Jeans",
            colorHex = "#1E293B",
            colorName = "Deep Indigo",
            material = "13.5oz Kurabo Mills Selvedge",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Classic", "Smart Casual", "Essential"),
            description = "Clean, unwashed deep indigo jeans without artificial distressing. Develops personal patina."
        ),
        ClothingItem(
            id = "bottom_denim_white",
            name = "Ecru Straight Denim",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Jeans",
            colorHex = "#F4EFEA",
            colorName = "Ecru / Natural White",
            material = "100% Cotton Denim",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.SPRING,
            styleTags = listOf("Old Money", "Summer", "Editorial"),
            description = "Unbleached natural ecru flecked with cotton seed husks. High-impact sartorial pairing."
        ),
        ClothingItem(
            id = "bottom_linen_olive",
            name = "Drawstring Tailored Linen Trousers",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Linen trousers",
            colorHex = "#5B6E4E",
            colorName = "Sage Olive",
            material = "100% Belgian Linen",
            formality = FormalityLevel.CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Resort", "Summer", "Comfort"),
            description = "Comfort of a drawstring back with the refined tailored front of trousers."
        ),
        ClothingItem(
            id = "bottom_shorts_chino",
            name = "7-Inch Tailored Chino Shorts",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Chino shorts",
            colorHex = "#E6D5B8",
            colorName = "Warm Khaki",
            material = "Compact Cotton Twill",
            formality = FormalityLevel.CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Summer", "Weekend", "Classic"),
            description = "Hits cleanly above the knee with a tailored taper. Zero pocket flare."
        ),
        ClothingItem(
            id = "bottom_joggers_charcoal",
            name = "Tailored French Terry Joggers",
            category = ClothingCategory.BOTTOMS,
            subcategory = "Joggers",
            colorHex = "#1E293B",
            colorName = "Charcoal Ink",
            material = "Organic Cotton Terry",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Athleisure", "Travel", "Comfort"),
            description = "Trim cuffs and concealed zip pockets keep casual loungewear looking intentional."
        ),

        // FOOTWEAR
        ClothingItem(
            id = "shoes_sneaker_white",
            name = "Minimalist Calfskin White Sneakers",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Minimal leather sneakers",
            colorHex = "#FFFFFF",
            colorName = "Clean White",
            material = "Full-Grain Italian Calfskin",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Minimalist", "Everyday", "Essential"),
            description = "Low-profile Margom rubber cupsole, waxed laces, and unmarked leather upper. Bridges casual and tailoring."
        ),
        ClothingItem(
            id = "shoes_loafer_brown",
            name = "Handcrafted Penny Loafers",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Loafers",
            colorHex = "#8B4513",
            colorName = "Dark Cognac Brown",
            material = "Burnished Calf Leather",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Old Money", "Smart Casual", "Classic"),
            description = "Goodyear welted with leather sole and almond toe shape. Essential for smart-casual tailoring."
        ),
        ClothingItem(
            id = "shoes_loafer_suede_sand",
            name = "Unlined Suede Belgian Loafers",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Loafers",
            colorHex = "#C4A482",
            colorName = "Sand Suede",
            material = "Italian Suede",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Old Money", "Summer", "Quiet Luxury"),
            description = "Glove-soft suede with subtle piped detailing and rubber island heel."
        ),
        ClothingItem(
            id = "shoes_boots_chelsea",
            name = "Classic Snuff Suede Chelsea Boots",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Chelsea boots",
            colorHex = "#78350F",
            colorName = "Snuff Brown",
            material = "Repello Suede with Dainite Sole",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.AUTUMN,
            styleTags = listOf("Smart Casual", "Autumn", "Modern"),
            description = "Slim ankle profile slips cleanly under tailored trouser openings without catching."
        ),
        ClothingItem(
            id = "shoes_oxford_black",
            name = "Cap-Toe Oxford Formal Shoes",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Oxford shoes",
            colorHex = "#09090B",
            colorName = "High-Shine Black",
            material = "Box Calf Leather",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Black Tie", "Business"),
            description = "Closed lacing cap-toe construction with channelled leather sole. The standard for suit attire."
        ),
        ClothingItem(
            id = "shoes_derby_brown",
            name = "Pebble-Grain Blucher Derby Shoes",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Derby shoes",
            colorHex = "#451A03",
            colorName = "Espresso Brown",
            material = "Pebble-Grain Leather",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Smart Casual", "Classic", "Heritage"),
            description = "Open lacing allows flexibility across insteps, and rugged storm welt handles varied terrain."
        ),
        ClothingItem(
            id = "shoes_sneaker_black",
            name = "Monochrome Suede Running Shoes",
            category = ClothingCategory.FOOTWEAR,
            subcategory = "Black sneakers",
            colorHex = "#18181B",
            colorName = "Pitch Black",
            material = "Suede & Technical Mesh",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Streetwear", "Athleisure", "Monochrome"),
            description = "All-black runner silhouette with comfortable EVA cushioning and understated branding."
        ),

        // OUTERWEAR
        ClothingItem(
            id = "outer_blazer_navy",
            name = "Unstructured Hopsack Navy Blazer",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Blazers",
            colorHex = "#0F172A",
            colorName = "Deep Navy",
            material = "Hopsack Wool (Unlined)",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Essential", "Smart Casual", "Old Money"),
            description = "Soft Neapolitan shoulders, patch pockets, and breathable open-weave hopsack. The foundation blazer."
        ),
        ClothingItem(
            id = "outer_overshirt_olive",
            name = "Heavy Cotton Canvas Overshirt",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Overshirts",
            colorHex = "#4A5540",
            colorName = "Forest Olive",
            material = "100% Cotton Moleskin",
            formality = FormalityLevel.CASUAL,
            season = Season.AUTUMN,
            styleTags = listOf("Casual", "Layering", "Workwear"),
            description = "Substantial weight works as a mid-layer or light jacket over plain white tees and knitwear."
        ),
        ClothingItem(
            id = "outer_coat_trench",
            name = "Double-Breasted Heritage Trench Coat",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Trench coats",
            colorHex = "#D4B996",
            colorName = "Camel Khaki",
            material = "Water-Resistant Cotton Gabardine",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.SPRING,
            styleTags = listOf("Classic", "Editorial", "Office"),
            description = "Horn buttons, belted waist, and storm flaps. Drapes sharply in rainy or transitional weather."
        ),
        ClothingItem(
            id = "outer_jacket_leather",
            name = "Minimalist Café Racer Leather Jacket",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Leather jackets",
            colorHex = "#18181B",
            colorName = "Matte Black",
            material = "Lambskin Leather",
            formality = FormalityLevel.CASUAL,
            season = Season.AUTUMN,
            styleTags = listOf("Modern", "Date Night", "Streetwear"),
            description = "Clean mandarin band collar with gunmetal dual zippers and zero extraneous hardware."
        ),
        ClothingItem(
            id = "outer_coat_overcoat",
            name = "Tailored Double-Breasted Wool Overcoat",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Wool coats",
            colorHex = "#1E293B",
            colorName = "Charcoal Navy",
            material = "Cashmere & Melange Wool",
            formality = FormalityLevel.FORMAL,
            season = Season.WINTER,
            styleTags = listOf("Formal", "Old Money", "Winter"),
            description = "Peak lapels, ticket pocket, and calf-length drape that commands presence over suits."
        ),
        ClothingItem(
            id = "outer_jacket_denim",
            name = "Vintage Wash Type-III Trucker Jacket",
            category = ClothingCategory.OUTERWEAR,
            subcategory = "Denim jackets",
            colorHex = "#64748B",
            colorName = "Washed Indigo Grey",
            material = "100% Rigid Cotton",
            formality = FormalityLevel.CASUAL,
            season = Season.SPRING,
            styleTags = listOf("Casual", "Vintage", "Layering"),
            description = "Cropped length complements higher-waisted chinos and trousers perfectly."
        ),

        // ACCESSORIES
        ClothingItem(
            id = "acc_belt_brown",
            name = "Hand-Stitched Bridle Leather Belt",
            category = ClothingCategory.ACCESSORIES,
            subcategory = "Leather belts",
            colorHex = "#8B4513",
            colorName = "Dark Cognac",
            material = "English Bridle Leather & Brass",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Essential", "Classic"),
            description = "Solid brass buckle with beveled painted edges. Always matches cognac/brown footwear."
        ),
        ClothingItem(
            id = "acc_belt_black",
            name = "Polished Dress Belt with Palladium Buckle",
            category = ClothingCategory.ACCESSORIES,
            subcategory = "Leather belts",
            colorHex = "#09090B",
            colorName = "Onyx Black",
            material = "French Calfskin",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Essential"),
            description = "30mm width designed specifically for formal suit trouser belt loops."
        ),
        ClothingItem(
            id = "acc_watch_minimal",
            name = "Bauhaus Dress Watch on Leather Strap",
            category = ClothingCategory.ACCESSORIES,
            subcategory = "Watches",
            colorHex = "#D4AF37",
            colorName = "Champagne Gold & Brown",
            material = "Stainless Steel & Sapphire Crystal",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Quiet Luxury", "Classic"),
            description = "38mm clean dial that slides effortlessly beneath tailored shirt cuffs."
        ),
        ClothingItem(
            id = "acc_sunglasses_tortoise",
            name = "Keyhole Bridge Acetate Sunglasses",
            category = ClothingCategory.ACCESSORIES,
            subcategory = "Sunglasses",
            colorHex = "#78350F",
            colorName = "Havana Tortoiseshell",
            material = "Mazzucchelli Acetate & Green Glass Lenses",
            formality = FormalityLevel.CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Summer", "Old Money"),
            description = "Timeless rounded silhouette that balances sharp jawlines and tailored ensembles."
        ),
        ClothingItem(
            id = "acc_bag_weekender",
            name = "Waxed Canvas & Leather Holdall",
            category = ClothingCategory.ACCESSORIES,
            subcategory = "Bags",
            colorHex = "#4A5540",
            colorName = "Olive & Tobacco",
            material = "Waterproof Waxed Canvas & Leather",
            formality = FormalityLevel.CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Travel", "Classic"),
            description = "Understated weekender silhouette that ages with distinctive character."
        ),

        // SOCKS
        ClothingItem(
            id = "sock_dress_charcoal",
            name = "Merino Wool Ribbed Dress Socks",
            category = ClothingCategory.SOCKS,
            subcategory = "Dress socks",
            colorHex = "#334155",
            colorName = "Dark Charcoal",
            material = "Fine Merino Wool Blend",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Office"),
            description = "Over-the-calf fit prevents unsightly skin showing when crossing legs while seated."
        ),
        ClothingItem(
            id = "sock_noshow_invisible",
            name = "Silicone-Grip No-Show Loafer Socks",
            category = ClothingCategory.SOCKS,
            subcategory = "No-show socks",
            colorHex = "#F4EFEA",
            colorName = "Nude Sand",
            material = "Bamboo Cotton Blend",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.SUMMER,
            styleTags = listOf("Summer", "Smart Casual"),
            description = "Deep cut throat remains completely invisible inside loafers and minimal sneakers."
        ),

        // FORMAL ACCESSORIES / TIES
        ClothingItem(
            id = "tie_silk_navy",
            name = "Grenadine Silk Necktie",
            category = ClothingCategory.FORMAL_ACCESSORIES,
            subcategory = "Classic neckties",
            colorHex = "#0F172A",
            colorName = "Midnight Navy",
            material = "100% Italian Grenadine Silk",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Classic", "Wedding"),
            description = "Garza fina open weave adds subtle tactile texture to formal poplin and twill shirts."
        ),
        ClothingItem(
            id = "tie_knit_forest",
            name = "Square-End Silk Knit Tie",
            category = ClothingCategory.FORMAL_ACCESSORIES,
            subcategory = "Knit ties",
            colorHex = "#2E473B",
            colorName = "Deep Forest Green",
            material = "100% Knitted Silk",
            formality = FormalityLevel.SMART_CASUAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Smart Casual", "Ivy"),
            description = "Relaxed square bottom and crunchy texture, ideal for unstructured sport coats."
        ),
        ClothingItem(
            id = "acc_pocket_square",
            name = "Hand-Rolled Silk Pocket Square",
            category = ClothingCategory.FORMAL_ACCESSORIES,
            subcategory = "Pocket squares",
            colorHex = "#FAF8F5",
            colorName = "Ivory with Navy Hem",
            material = "Pure Mulberry Silk",
            formality = FormalityLevel.FORMAL,
            season = Season.ALL_SEASON,
            styleTags = listOf("Formal", "Sartorial"),
            description = "Crisp TV fold or relaxed puff adds refined polish to breast pockets."
        )
    )

    // Lookup helper
    fun findItem(id: String): ClothingItem {
        return clothingItems.find { it.id == id }
            ?: clothingItems.first()
    }

    // --- COLOR COMBINATIONS ---
    val colorCombinations: List<ColorCombination> = listOf(
        ColorCombination(
            id = "color_navy_grey",
            name = "Navy & Mid Grey",
            primaryHex = "#0F172A",
            secondaryHex = "#64748B",
            accentHex = "#D4AF37",
            topColorName = "Midnight Navy (Shirt or Blazer)",
            bottomColorName = "Mid Grey Flannel or Wool",
            footwearColorName = "Dark Cognac Loafers or Black Oxfords",
            beltAndSocksAdvice = "Dark brown leather belt with charcoal dress socks.",
            formalityLevel = FormalityLevel.SMART_CASUAL,
            bestOccasions = listOf("Business Meetings", "Executive Dinners", "Gallery Openings"),
            seasonalSuitability = listOf(Season.AUTUMN, Season.WINTER, Season.SPRING),
            stylingRationale = "The definitive menswear power pairing. The cool authority of navy anchors the soft neutral balance of grey."
        ),
        ColorCombination(
            id = "color_cream_navy",
            name = "Cream & Navy",
            primaryHex = "#FAF8F5",
            secondaryHex = "#0F172A",
            accentHex = "#C4A482",
            topColorName = "Warm Cream Knit Polo or Ecru Linen",
            bottomColorName = "Deep Navy Chinos or High-Twist Trousers",
            footwearColorName = "Clean White Minimal Sneakers or Brown Loafers",
            beltAndSocksAdvice = "Woven suede belt; no-show socks in warmer months.",
            formalityLevel = FormalityLevel.SMART_CASUAL,
            bestOccasions = listOf("Summer Dinners", "Weekend Brunches", "Casual Fridays"),
            seasonalSuitability = listOf(Season.SPRING, Season.SUMMER),
            stylingRationale = "High contrast without being harsh. Cream radiates warmth that softens the structure of navy."
        ),
        ColorCombination(
            id = "color_olive_beige",
            name = "Olive Green & Sand Beige",
            primaryHex = "#4A5540",
            secondaryHex = "#D4B996",
            accentHex = "#78350F",
            topColorName = "Forest Olive Overshirt or Camp Shirt",
            bottomColorName = "Sand Beige Chinos or Linen Shorts",
            footwearColorName = "Snuff Suede Chelsea Boots or Sand Loafers",
            beltAndSocksAdvice = "Cognac leather belt with earthy tan socks.",
            formalityLevel = FormalityLevel.CASUAL,
            bestOccasions = listOf("Weekend Travel", "Outdoor Gatherings", "Coffee Dates"),
            seasonalSuitability = listOf(Season.AUTUMN, Season.SPRING),
            stylingRationale = "Organic, earthy tones that ground the silhouette. Flattering on all complexions and evokes rugged elegance."
        ),
        ColorCombination(
            id = "color_monochrome_charcoal",
            name = "Charcoal & Onyx Black",
            primaryHex = "#18181B",
            secondaryHex = "#334155",
            accentHex = "#E2E8F0",
            topColorName = "Pitch Black Crewneck or Fine Rollneck",
            bottomColorName = "Charcoal Slate Trousers or Raw Indigo Denim",
            footwearColorName = "Minimal Black Leather Sneakers or Black Chelsea Boots",
            beltAndSocksAdvice = "Matte black dress belt with black ribbed socks.",
            formalityLevel = FormalityLevel.SMART_CASUAL,
            bestOccasions = listOf("Night Out", "Creative Workspaces", "Evening Cocktails"),
            seasonalSuitability = listOf(Season.AUTUMN, Season.WINTER),
            stylingRationale = "Architectural and minimalist. By mixing textures (matte cotton with wool flannel), monochrome avoids looking flat."
        ),
        ColorCombination(
            id = "color_lightblue_white",
            name = "Sky Blue & Crisp White",
            primaryHex = "#93C5FD",
            secondaryHex = "#FFFFFF",
            accentHex = "#8B4513",
            topColorName = "Sky Blue Oxford Shirt or Poplin",
            bottomColorName = "Ecru Denim or White Linen Trousers",
            footwearColorName = "Burnished Penny Loafers in Cognac",
            beltAndSocksAdvice = "Dark brown bridle leather belt; invisible no-show socks.",
            formalityLevel = FormalityLevel.SMART_CASUAL,
            bestOccasions = listOf("Garden Parties", "Coastal Getaways", "Summer Weddings"),
            seasonalSuitability = listOf(Season.SUMMER, Season.SPRING),
            stylingRationale = "Breezy Mediterranean aesthetic. Clean white trousers accentuate the airy pastel of the sky blue top."
        ),
        ColorCombination(
            id = "color_camel_black",
            name = "Camel Tan & Deep Black",
            primaryHex = "#C4A482",
            secondaryHex = "#18181B",
            accentHex = "#D4AF37",
            topColorName = "Camel Wool Overcoat or Merino Crewneck",
            bottomColorName = "Black Tailored Trousers or Dark Denim",
            footwearColorName = "Polished Black Derby Shoes or Clean Black Boots",
            beltAndSocksAdvice = "Black calfskin belt with gold buckle detail.",
            formalityLevel = FormalityLevel.SMART_CASUAL,
            bestOccasions = listOf("City Nights", "Autumn Commutes", "Date Nights"),
            seasonalSuitability = listOf(Season.AUTUMN, Season.WINTER),
            stylingRationale = "Striking luxury contrast. The warm richness of camel cuts through the sleek density of pure black."
        ),
        ColorCombination(
            id = "color_darkgreen_khaki",
            name = "Deep Emerald & Warm Khaki",
            primaryHex = "#2E473B",
            secondaryHex = "#E6D5B8",
            accentHex = "#78350F",
            topColorName = "Deep Forest Green Knit or Overshirt",
            bottomColorName = "Warm Khaki Chinos",
            footwearColorName = "Brown Suede Derby Shoes",
            beltAndSocksAdvice = "Woven dark brown belt with heather olive socks.",
            formalityLevel = FormalityLevel.CASUAL,
            bestOccasions = listOf("Countryside Strolls", "Casual Dinners", "Creative Meetings"),
            seasonalSuitability = listOf(Season.AUTUMN, Season.SPRING),
            stylingRationale = "Understated British heritage palette that feels both grounded and intentionally curated."
        ),
        ColorCombination(
            id = "color_white_grey",
            name = "Optic White & Heather Grey",
            primaryHex = "#FFFFFF",
            secondaryHex = "#CBD5E1",
            accentHex = "#0F172A",
            topColorName = "Crisp White T-Shirt or Linen Shirt",
            bottomColorName = "Heather Grey Trousers or Tailored Shorts",
            footwearColorName = "White Minimal Leather Sneakers",
            beltAndSocksAdvice = "Navy woven belt for subtle accent contrast.",
            formalityLevel = FormalityLevel.CASUAL,
            bestOccasions = listOf("Airport Travel", "Weekend Strolls", "Morning Coffee"),
            seasonalSuitability = listOf(Season.SPRING, Season.SUMMER),
            stylingRationale = "Effortless Scandinavian minimalism. Clean, bright, and universally flattering in natural daylight."
        )
    )

    // --- CURATED OUTFITS ---
    val outfits: List<Outfit> = listOf(
        Outfit(
            id = "outfit_neapolitan_tailored",
            title = "The Sartorial Neapolitan",
            styleCategory = "Old Money",
            occasion = "Office & Evenings",
            season = Season.ALL_SEASON,
            top = findItem("top_oxford_white"),
            bottom = findItem("bottom_trouser_grey"),
            shoes = findItem("shoes_loafer_brown"),
            outerwear = findItem("outer_blazer_navy"),
            belt = findItem("acc_belt_brown"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_dress_charcoal"),
            tie = findItem("tie_silk_navy"),
            accessory = findItem("acc_pocket_square"),
            colorPalette = listOf("#0F172A", "#64748B", "#FFFFFF", "#8B4513"),
            stylingNotes = "The pinnacle of masculine tailoring: a hopsack navy blazer with grey flannel trousers. The unlined jacket keeps shoulders natural.",
            fitGuidance = "Ensure the blazer sleeves leave 1/2 inch of shirt cuff visible. Trousers should feature a clean medium break over the loafers.",
            formalityScore = 4
        ),
        Outfit(
            id = "outfit_riviera_casual",
            title = "Riviera Knit Elegance",
            styleCategory = "Quiet Luxury",
            occasion = "Date Night",
            season = Season.SUMMER,
            top = findItem("top_knit_polo_cream"),
            bottom = findItem("bottom_trouser_navy"),
            shoes = findItem("shoes_loafer_suede_sand"),
            outerwear = null,
            belt = null,
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_noshow_invisible"),
            accessory = findItem("acc_sunglasses_tortoise"),
            colorPalette = listOf("#FAF8F5", "#0F172A", "#C4A482", "#78350F"),
            stylingNotes = "A Johnny-collar knit polo paired with high-waisted navy trousers. The contrast between warm ivory and deep navy feels effortless yet elevated.",
            fitGuidance = "The knit polo should fit snug around the arms and chest with room at the waist. Wear trousers with no belt for clean lines.",
            formalityScore = 3
        ),
        Outfit(
            id = "outfit_modern_minimalist",
            title = "The Architectural Minimalist",
            styleCategory = "Minimalist",
            occasion = "Everyday",
            season = Season.ALL_SEASON,
            top = findItem("top_tee_heavy_white"),
            bottom = findItem("bottom_denim_selvedge"),
            shoes = findItem("shoes_sneaker_white"),
            outerwear = findItem("outer_overshirt_olive"),
            belt = findItem("acc_belt_brown"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_noshow_invisible"),
            accessory = findItem("acc_bag_weekender"),
            colorPalette = listOf("#4A5540", "#F8FAFC", "#1E293B", "#FFFFFF"),
            stylingNotes = "A dense white tee underneath an olive canvas overshirt, balanced by dark Japanese selvedge denim and crisp white calfskin sneakers.",
            fitGuidance = "Overshirt should fall straight without pulling at the chest. Single cuff on the denim to showcase the selvedge ID line.",
            formalityScore = 2
        ),
        Outfit(
            id = "outfit_smart_weekend",
            title = "Autumn City Transit",
            styleCategory = "Smart Casual",
            occasion = "Weekend",
            season = Season.AUTUMN,
            top = findItem("top_merino_sweater_camel"),
            bottom = findItem("bottom_chino_beige"),
            shoes = findItem("shoes_boots_chelsea"),
            outerwear = findItem("outer_jacket_leather"),
            belt = findItem("acc_belt_brown"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_dress_charcoal"),
            colorPalette = listOf("#C4A482", "#18181B", "#D4B996", "#78350F"),
            stylingNotes = "Camel merino wool brings warmth and refinement beneath a sleek matte black leather racer jacket, finished with snuff suede boots.",
            fitGuidance = "Leather jacket should fit slim and end just at the beltline. Keep sweater cuffs tucked under jacket sleeves.",
            formalityScore = 3
        ),
        Outfit(
            id = "outfit_summer_resort",
            title = "St. Tropez Linen Breeze",
            styleCategory = "Old Money",
            occasion = "Weekend",
            season = Season.SUMMER,
            top = findItem("top_linen_cream"),
            bottom = findItem("bottom_linen_olive"),
            shoes = findItem("shoes_loafer_suede_sand"),
            outerwear = null,
            belt = null,
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_noshow_invisible"),
            accessory = findItem("acc_sunglasses_tortoise"),
            colorPalette = listOf("#F4EFEA", "#5B6E4E", "#C4A482", "#78350F"),
            stylingNotes = "Double-linen coordination: warm sand camp-collar shirt worn open-chested with relaxed sage olive drawstring trousers.",
            fitGuidance = "Embrace natural wrinkles—linen thrives in relaxed movement. Sleeves can be rolled up twice loosely.",
            formalityScore = 2
        ),
        Outfit(
            id = "outfit_monochrome_evening",
            title = "Nocturne Tailored Monochrome",
            styleCategory = "Quiet Luxury",
            occasion = "Evening Events",
            season = Season.WINTER,
            top = findItem("top_turtleneck_charcoal"),
            bottom = findItem("bottom_trouser_navy"),
            shoes = findItem("shoes_boots_chelsea"),
            outerwear = findItem("outer_coat_overcoat"),
            belt = findItem("acc_belt_black"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_dress_charcoal"),
            colorPalette = listOf("#1E293B", "#334155", "#0F172A", "#09090B"),
            stylingNotes = "Charcoal rollneck turtleneck layered inside a structured cashmere overcoat. Dramatic, sleek, and exceptionally flattering.",
            fitGuidance = "Turtleneck collar should be folded once cleanly. Overcoat length should sit just below the knee for dramatic sweep.",
            formalityScore = 4
        ),
        Outfit(
            id = "outfit_black_tie_formal",
            title = "Black Tie Formal Gala",
            styleCategory = "Formal Business",
            occasion = "Wedding Guest",
            season = Season.ALL_SEASON,
            top = findItem("top_dress_shirt_twill"),
            bottom = findItem("bottom_trouser_navy"),
            shoes = findItem("shoes_oxford_black"),
            outerwear = findItem("outer_blazer_navy"),
            belt = findItem("acc_belt_black"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_dress_charcoal"),
            tie = findItem("tie_silk_navy"),
            accessory = findItem("acc_pocket_square"),
            colorPalette = listOf("#0F172A", "#FFFFFF", "#09090B", "#D4AF37"),
            stylingNotes = "Crisp white twill dress shirt with high-shine black oxford dress shoes and midnight navy tailoring.",
            fitGuidance = "Zero collar gap behind the neck. Pants hemmed with slight slant and zero-break over dress shoes.",
            formalityScore = 5
        ),
        Outfit(
            id = "outfit_relaxed_street",
            title = "Elevated Downtown Athleisure",
            styleCategory = "Streetwear",
            occasion = "Weekend",
            season = Season.ALL_SEASON,
            top = findItem("top_hoodie_grey"),
            bottom = findItem("bottom_joggers_charcoal"),
            shoes = findItem("shoes_sneaker_black"),
            outerwear = findItem("outer_jacket_denim"),
            watch = findItem("acc_watch_minimal"),
            socks = findItem("sock_noshow_invisible"),
            accessory = findItem("acc_bag_weekender"),
            colorPalette = listOf("#CBD5E1", "#1E293B", "#64748B", "#18181B"),
            stylingNotes = "Elevates casual staples by layering a cropped trucker denim jacket over a loopback grey hoodie.",
            fitGuidance = "Hoodie should not extend more than 2 inches beneath the denim jacket waistband.",
            formalityScore = 1
        )
    )

    // --- STYLE GUIDES ---
    val styleGuides: List<StyleGuide> = listOf(
        StyleGuide(
            id = "guide_old_money",
            title = "Old Money / Ivy League",
            tagLine = "Understated heritage, generational quality, zero loud logos",
            aestheticPhilosophy = "The Old Money aesthetic focuses on quiet refinement: high-twist wools, bespoke tailoring, natural shoulder lines, and garments designed to look better with age.",
            keySartorialRule = "Buy fewer, higher-quality items in neutral shades. Avoid visible branding or aggressive synthetic sheen.",
            coreGarments = listOf("Navy hopsack blazer", "Oxford cloth button-downs", "Pleated flannel trousers", "Cable knit sweaters", "Penny loafers"),
            recommendedColorPairs = listOf("Navy" to "#0F172A", "Warm Ivory" to "#FAF8F5", "Camel" to "#C4A482", "Mid Grey" to "#64748B"),
            footwearRecommendations = listOf("Burnished leather penny loafers", "Suede Belgian loafers", "Derby dress shoes"),
            accessoryGuidelines = listOf("Vintage or Bauhaus leather-strap dress watch", "Brass buckle bridle belt", "Silk pocket square with TV fold"),
            commonMistakesToAvoid = listOf("Flashy designer belt buckles", "Skinny-fit cropped suit pants", "Overly shiny polyester fabrics"),
            primeOccasions = listOf("Country club events", "Formal family gatherings", "Gallery openings", "Summer polo matches")
        ),
        StyleGuide(
            id = "guide_quiet_luxury",
            title = "Quiet Luxury",
            tagLine = "Tactile indulgence, immaculate proportions, subtle authority",
            aestheticPhilosophy = "Quiet luxury relies on tactile mastery: buttery Italian suede, double-faced cashmere, silk-linen blends, and meticulously calibrated monochrome silhouettes.",
            keySartorialRule = "Let texture speak louder than color. A monochrome outfit succeeds entirely through contrasting fabric textures.",
            coreGarments = listOf("Johnny-collar knit polo", "Fine cashmere turtleneck", "Unlined suede blouson", "Drawstring tailored trousers"),
            recommendedColorPairs = listOf("Charcoal Slate" to "#334155", "Sand Taupe" to "#D4B996", "Midnight Navy" to "#0F172A", "Warm Ivory" to "#FAF8F5"),
            footwearRecommendations = listOf("Minimalist white calfskin sneakers", "Unlined suede slip-ons", "Snuff Chelsea boots"),
            accessoryGuidelines = listOf("Minimalist mechanical watch", "Tortoiseshell sunglasses with polarized green lenses"),
            commonMistakesToAvoid = listOf("Visible brand logos", "Loud neon accents", "Ill-fitting shoulders"),
            primeOccasions = listOf("Private dining", "Tech executive retreats", "Art fairs", "Cocktail lounges")
        ),
        StyleGuide(
            id = "guide_smart_casual",
            title = "Smart Casual",
            tagLine = "Bridging the gap between structure and relaxed ease",
            aestheticPhilosophy = "The modern workplace standard. Combines tailored upper pieces (like an unstructured blazer or knit polo) with casual lower pieces (like dark denim or chinos).",
            keySartorialRule = "Keep one half of the outfit structured and the other half relaxed. For instance: structured blazer with casual denim, or tailored trousers with a clean crewneck.",
            coreGarments = listOf("Unstructured sport coat", "Clean Oxford shirt", "Tailored chinos", "Selvedge denim", "Merino crewneck"),
            recommendedColorPairs = listOf("Navy" to "#0F172A", "Olive" to "#4A5540", "Beige" to "#D4B996", "White" to "#FFFFFF"),
            footwearRecommendations = listOf("Minimal white leather sneakers", "Suede Chelsea boots", "Brown penny loafers"),
            accessoryGuidelines = listOf("Leather strap watch", "Smart leather weekender holdall"),
            commonMistakesToAvoid = listOf("Distressed or ripped jeans", "Athletic gym running shoes", "Wrinkled shirts"),
            primeOccasions = listOf("Modern office", "First dates", "Dinner parties", "Client lunches")
        ),
        StyleGuide(
            id = "guide_minimalist",
            title = "Modern Minimalist",
            tagLine = "Function, geometric clarity, monochrome restraint",
            aestheticPhilosophy = "Rooted in Scandinavian and Japanese design principles. Strips away ornamentation to emphasize silhouette, seam construction, and neutral harmony.",
            keySartorialRule = "Limit the outfit to maximum 3 neutral colors. Emphasize crisp geometry and clean necklines.",
            coreGarments = listOf("Heavyweight boxy crewneck tee", "Relaxed straight trousers", "Overshirt jacket", "Single-breasted mac coat"),
            recommendedColorPairs = listOf("Pitch Black" to "#18181B", "Optic White" to "#FFFFFF", "Heather Grey" to "#CBD5E1", "Charcoal" to "#334155"),
            footwearRecommendations = listOf("Pristine white cupsole sneakers", "All-black leather derby shoes"),
            accessoryGuidelines = listOf("Brushed silver signet ring", "Clean tote bag", "Black leather cardholder"),
            commonMistakesToAvoid = listOf("Cluttered accessories", "Over-branding", "Faded or pilled cottons"),
            primeOccasions = listOf("Daily wear", "Creative studio meetings", "City commuting", "Coffee outings")
        ),
        StyleGuide(
            id = "guide_streetwear",
            title = "Elevated Streetwear",
            tagLine = "Architectural drape, utilitarian details, contemporary edge",
            aestheticPhilosophy = "High-low fashion fusion that mixes premium athletic wear, utilitarian workwear, and modern cuts with clean sneakers and layering.",
            keySartorialRule = "Play with volume intentionally: pair a relaxed, boxy top with tapered pants, or slim knitwear with wide-leg trousers.",
            coreGarments = listOf("French terry loopback hoodie", "Reverse sateen cargo trousers", "Denim trucker jacket", "Oversized graphic tee"),
            recommendedColorPairs = listOf("Vintage Grey" to "#CBD5E1", "Military Olive" to "#4A5540", "Indigo" to "#1E293B", "Black" to "#18181B"),
            footwearRecommendations = listOf("Heritage court sneakers", "Suede retro runners", "Chunky sole derbies"),
            accessoryGuidelines = listOf("Beanie or low-profile cap", "Crossbody utility bag"),
            commonMistakesToAvoid = listOf("Baggy-on-baggy that swallows frame", "Cheap synthetic tracksuits"),
            primeOccasions = listOf("Weekend hangouts", "Concerts", "Street photography", "Travel")
        )
    )

    // --- MEN'S POSTURE & PHOTOGRAPHY POSES ---
    val poseGuides: List<PoseGuide> = listOf(
        PoseGuide(
            id = "pose_natural_standing",
            title = "The Confident S-Curve Standing Pose",
            category = "Standing",
            postureCues = listOf(
                "Shift 70% of your body weight onto your back leg.",
                "Let the front leg relax slightly forward with a gentle knee bend.",
                "Roll shoulders backward, drop them down, and engage core lightly.",
                "Elongate the neck as if a thread is pulling the crown of your head upward."
            ),
            armAndHandPosition = "Slip one hand loosely into your trouser pocket with thumb hooked outside. Let the other arm hang naturally with relaxed fingers.",
            footAndLegPlacement = "Feet shoulder-width apart, angled approximately 30 degrees outward rather than pointing straight into the camera.",
            shoulderAndHeadAlignment = "Turn your chest 20-30 degrees away from the camera lens while keeping your chin level and eyes towards the lens.",
            cameraAngleAndFraming = "Shoot from mid-chest to hip height with a 50mm or 85mm lens equivalent to prevent leg foreshortening.",
            mistakesToAvoid = listOf(
                "Standing flat-footed like a passport photo.",
                "Clenching fists tight into pockets.",
                "Hunching shoulders forward, collapsing the lapels."
            ),
            complementaryOutfits = listOf("Smart Casual tailoring", "Blazer + Chino combinations", "Overcoat winter outfits"),
            confidenceNote = "Relaxing the front leg naturally creates asymmetry, which looks photogenic and effortless."
        ),
        PoseGuide(
            id = "pose_walking_candid",
            title = "The Purposeful Stride (Candid Walking)",
            category = "Walking",
            postureCues = listOf(
                "Walk at 50% of your normal speed towards or across the camera.",
                "Keep your gaze forward at eye-level or looking down past the camera.",
                "Take confident, slightly elongated steps.",
                "Keep spine upright without stiffening your posture."
            ),
            armAndHandPosition = "Natural counter-swing with your walking stride. Alternatively, button or hold one lapel lightly.",
            footAndLegPlacement = "Mid-stride capture when the leading heel just touches the ground and trailing toe pushes off.",
            shoulderAndHeadAlignment = "Shoulders square to the path of travel, chin parallel to pavement.",
            cameraAngleAndFraming = "Slightly low camera angle (knee to waist level) to emphasize stature and coat movement.",
            mistakesToAvoid = listOf(
                "Staring directly into the camera while walking.",
                "Swinging arms excessively like military marching.",
                "Looking down at your feet."
            ),
            complementaryOutfits = listOf("Long trench coats", "Wool overcoats", "Casual overshirt and selvedge denim"),
            confidenceNote = "Movement introduces dynamic fabric drape and prevents any feeling of posed stiffness."
        ),
        PoseGuide(
            id = "pose_casual_lean",
            title = "The Architectural Wall Lean",
            category = "Leaning",
            postureCues = listOf(
                "Rest your upper back or one shoulder against a textured wall (brick, concrete, stone).",
                "Keep your feet 12 to 18 inches away from the wall base.",
                "Avoid sliding or collapsing your lower back."
            ),
            armAndHandPosition = "Cross arms loosely across the chest (hands tucked gently under biceps) OR one hand in pocket with the other adjusting your watch.",
            footAndLegPlacement = "Cross the leg closest to the camera over the stationary back leg, resting the toe lightly on the pavement.",
            shoulderAndHeadAlignment = "Tilt head subtly toward the camera with a relaxed half-smile or calm neutral expression.",
            cameraAngleAndFraming = "Shoot diagonally along the wall surface to create leading perspective lines.",
            mistakesToAvoid = listOf(
                "Slumping heavily against the wall like you're exhausted.",
                "Dirtying your jacket back on untreated chalky surfaces.",
                "Burying your neck into your shoulders."
            ),
            complementaryOutfits = listOf("Leather jackets", "Denim trucker jackets", "Knit polo with chinos"),
            confidenceNote = "Walls ground the subject in the environment and naturally encourage a relaxed posture."
        ),
        PoseGuide(
            id = "pose_seated_cafe",
            title = "The Parisian Café Seated Pose",
            category = "Seated",
            postureCues = listOf(
                "Sit on the front half of the chair rather than sinking back into the cushions.",
                "Maintain a gentle forward torso lean of about 10 degrees to show engagement.",
                "Keep your spine elongated."
            ),
            armAndHandPosition = "Rest one forearm comfortably across the table edge holding an espresso cup or sunglasses. Rest the other hand on your lap.",
            footAndLegPlacement = "Cross one ankle over the opposite knee (Figure-4 leg cross) or keep both feet flat with knees slightly apart.",
            shoulderAndHeadAlignment = "Head angled toward natural ambient light source (window or open street).",
            cameraAngleAndFraming = "Eye-level from across the table, shallow depth of field blurring the background.",
            mistakesToAvoid = listOf(
                "Slouching into the chair backrest, bunching your shirt belly.",
                "Crossing legs tightly in narrow formal trousers.",
                "Resting your chin heavy on your hand, squishing your jawline."
            ),
            complementaryOutfits = listOf("Linen shirts", "Merino sweaters", "Casual sport coats"),
            confidenceNote = "Sitting forward projects quiet charisma and warmth."
        ),
        PoseGuide(
            id = "pose_watch_adjustment",
            title = "The Watch & Cuff Detail Interaction",
            category = "Portrait",
            postureCues = listOf(
                "Stand tall with weight on the back leg.",
                "Bring the watch wrist up to mid-torso height.",
                "Look down thoughtfully at your timepiece or off-camera."
            ),
            armAndHandPosition = "Use the opposite hand to gently adjust the strap, bezel, or pull down your shirt cuff.",
            footAndLegPlacement = "Standard relaxed stance with feet 10 inches apart.",
            shoulderAndHeadAlignment = "Head tilted 15 degrees downward, highlighting jawline and collar detailing.",
            cameraAngleAndFraming = "Medium close-up (waist to head) focused on the wrist, cuff, and tailored jacket sleeve.",
            mistakesToAvoid = listOf(
                "Staging the motion too aggressively like an infomercial.",
                "Tensing neck muscles while looking down."
            ),
            complementaryOutfits = listOf("Tailored suits", "Dress shirts with cufflinks", "Knitwear with dress watch"),
            confidenceNote = "Action-oriented micro-movements give your hands purpose and highlight sartorial accessories."
        ),
        PoseGuide(
            id = "pose_jacket_button",
            title = "The Executive Buttoning Pose",
            category = "Formal",
            postureCues = listOf(
                "Stand upright with shoulders back and chest open.",
                "Face 15 degrees off-center from the camera.",
                "Weight distributed evenly on both feet."
            ),
            armAndHandPosition = "Both hands brought together at the waist to button or unbutton the jacket's top button.",
            footAndLegPlacement = "Feet shoulder-width apart, planted firmly.",
            shoulderAndHeadAlignment = "Look directly into the camera lens with calm confidence, chin held level.",
            cameraAngleAndFraming = "Straight-on shot at chest level, framed from knee to head.",
            mistakesToAvoid = listOf(
                "Fastening both buttons on a two-button suit jacket (always leave the bottom button unfastened!).",
                "Looking stiff or stern; soften your eyes."
            ),
            complementaryOutfits = listOf("Two-piece formal suits", "Double-breasted blazers", "Tuxedos"),
            confidenceNote = "The classic gentleman's gesture that emphasizes waist suppression and suit silhouette."
        )
    )
}
