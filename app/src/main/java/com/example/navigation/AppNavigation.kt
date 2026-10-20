package com.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DryCleaning
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.DryCleaning
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.core.model.ClothingCategory
import com.example.feature.aistylist.AiStylistScreen
import com.example.feature.aistylist.AiStylistViewModel
import com.example.feature.colors.ColorExplorerScreen
import com.example.feature.colors.ColorExplorerViewModel
import com.example.feature.favorites.FavoritesScreen
import com.example.feature.favorites.FavoritesViewModel
import com.example.feature.home.HomeScreen
import com.example.feature.home.HomeViewModel
import com.example.feature.outfits.OutfitsScreen
import com.example.feature.outfits.OutfitsViewModel
import com.example.feature.poses.PoseGuideScreen
import com.example.feature.poses.PoseGuideViewModel
import com.example.feature.styleguide.StyleGuideScreen
import com.example.feature.styleguide.StyleGuideViewModel
import com.example.feature.wardrobe.WardrobeScreen
import com.example.feature.wardrobe.WardrobeViewModel
import com.example.ui.theme.GoldChampagne

enum class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isBottomBarItem: Boolean = true
) {
    DISCOVER("discover", "Discover", Icons.Filled.Explore, Icons.Outlined.Explore),
    OUTFITS("outfits", "Outfits", Icons.Filled.Checkroom, Icons.Outlined.Checkroom),
    WARDROBE("wardrobe", "Wardrobe", Icons.Filled.DryCleaning, Icons.Outlined.DryCleaning),
    STYLE_GUIDE("style_guide", "Style Guide", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
    SAVED("saved", "Saved", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder),

    // Sub-screens
    COLORS("colors", "Color Harmony", Icons.Filled.ColorLens, Icons.Filled.ColorLens, false),
    POSES("poses", "Pose Guide", Icons.Filled.DirectionsWalk, Icons.Filled.DirectionsWalk, false),
    AI_STYLIST("ai_stylist", "AI Stylist", Icons.Filled.AutoAwesome, Icons.Filled.AutoAwesome, false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    homeViewModel: HomeViewModel,
    outfitsViewModel: OutfitsViewModel,
    wardrobeViewModel: WardrobeViewModel,
    styleGuideViewModel: StyleGuideViewModel,
    colorExplorerViewModel: ColorExplorerViewModel,
    poseGuideViewModel: PoseGuideViewModel,
    favoritesViewModel: FavoritesViewModel,
    aiStylistViewModel: AiStylistViewModel
) {
    var currentScreen by remember { mutableStateOf(Screen.DISCOVER) }

    // Intercept Back button if on sub-screens
    if (currentScreen != Screen.DISCOVER) {
        BackHandler {
            currentScreen = Screen.DISCOVER
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentScreen.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                actions = {
                    if (currentScreen != Screen.AI_STYLIST) {
                        IconButton(
                            onClick = { currentScreen = Screen.AI_STYLIST },
                            modifier = Modifier.testTag("topbar_ai_stylist_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "AI Stylist",
                                tint = GoldChampagne
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val bottomScreens = Screen.values().filter { it.isBottomBarItem }
                bottomScreens.forEach { screen ->
                    val isSelected = currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                                contentDescription = screen.title
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag("nav_item_${screen.route}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                Screen.DISCOVER -> HomeScreen(
                    viewModel = homeViewModel,
                    onNavigateToOutfits = { currentScreen = Screen.OUTFITS },
                    onNavigateToWardrobe = { cat ->
                        wardrobeViewModel.selectCategory(cat)
                        currentScreen = Screen.WARDROBE
                    },
                    onNavigateToColors = { currentScreen = Screen.COLORS },
                    onNavigateToStyleGuide = { currentScreen = Screen.STYLE_GUIDE },
                    onNavigateToPoses = { currentScreen = Screen.POSES },
                    onNavigateToAiStylist = { currentScreen = Screen.AI_STYLIST },
                    onNavigateToSaved = { currentScreen = Screen.SAVED }
                )

                Screen.OUTFITS -> OutfitsScreen(
                    viewModel = outfitsViewModel
                )

                Screen.WARDROBE -> WardrobeScreen(
                    viewModel = wardrobeViewModel
                )

                Screen.STYLE_GUIDE -> StyleGuideScreen(
                    viewModel = styleGuideViewModel
                )

                Screen.SAVED -> FavoritesScreen(
                    viewModel = favoritesViewModel
                )

                Screen.COLORS -> ColorExplorerScreen(
                    viewModel = colorExplorerViewModel
                )

                Screen.POSES -> PoseGuideScreen(
                    viewModel = poseGuideViewModel
                )

                Screen.AI_STYLIST -> AiStylistScreen(
                    viewModel = aiStylistViewModel
                )
            }
        }
    }
}
