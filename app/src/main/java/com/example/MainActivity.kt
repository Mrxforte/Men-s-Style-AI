package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.core.database.AppDatabase
import com.example.core.repository.StyleRepositoryImpl
import com.example.feature.aistylist.AiStylistViewModel
import com.example.feature.aistylist.AiStylistViewModelFactory
import com.example.feature.colors.ColorExplorerViewModel
import com.example.feature.colors.ColorExplorerViewModelFactory
import com.example.feature.favorites.FavoritesViewModel
import com.example.feature.favorites.FavoritesViewModelFactory
import com.example.feature.home.HomeViewModel
import com.example.feature.home.HomeViewModelFactory
import com.example.feature.outfits.OutfitsViewModel
import com.example.feature.outfits.OutfitsViewModelFactory
import com.example.feature.poses.PoseGuideViewModel
import com.example.feature.poses.PoseGuideViewModelFactory
import com.example.feature.styleguide.StyleGuideViewModel
import com.example.feature.styleguide.StyleGuideViewModelFactory
import com.example.feature.wardrobe.WardrobeViewModel
import com.example.feature.wardrobe.WardrobeViewModelFactory
import com.example.navigation.MainAppScreen
import com.example.ui.theme.MensStyleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Clean Architecture Composition Root
        val database = AppDatabase.getInstance(applicationContext)
        val repository = StyleRepositoryImpl(database.styleDao())

        val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(repository) }
        val outfitsViewModel: OutfitsViewModel by viewModels { OutfitsViewModelFactory(repository) }
        val wardrobeViewModel: WardrobeViewModel by viewModels { WardrobeViewModelFactory(repository) }
        val styleGuideViewModel: StyleGuideViewModel by viewModels { StyleGuideViewModelFactory(repository) }
        val colorExplorerViewModel: ColorExplorerViewModel by viewModels { ColorExplorerViewModelFactory(repository) }
        val poseGuideViewModel: PoseGuideViewModel by viewModels { PoseGuideViewModelFactory(repository) }
        val favoritesViewModel: FavoritesViewModel by viewModels { FavoritesViewModelFactory(repository) }
        val aiStylistViewModel: AiStylistViewModel by viewModels { AiStylistViewModelFactory(repository) }

        setContent {
            MensStyleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainAppScreen(
                        homeViewModel = homeViewModel,
                        outfitsViewModel = outfitsViewModel,
                        wardrobeViewModel = wardrobeViewModel,
                        styleGuideViewModel = styleGuideViewModel,
                        colorExplorerViewModel = colorExplorerViewModel,
                        poseGuideViewModel = poseGuideViewModel,
                        favoritesViewModel = favoritesViewModel,
                        aiStylistViewModel = aiStylistViewModel
                    )
                }
            }
        }
    }
}
