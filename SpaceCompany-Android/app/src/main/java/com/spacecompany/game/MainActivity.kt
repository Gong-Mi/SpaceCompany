package com.spacecompany.game

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.spacecompany.game.data.SaveGameRepository
import com.spacecompany.game.ui.screens.MainScreen
import com.spacecompany.game.ui.theme.SpaceCompanyTheme
import com.spacecompany.game.viewmodel.GameViewModel
import com.spacecompany.game.viewmodel.GameViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate repository and factory
        val saveGameRepository = SaveGameRepository(this)
        val viewModelFactory = GameViewModelFactory(saveGameRepository)

        // Get ViewModel using the factory
        val gameViewModel: GameViewModel by viewModels { viewModelFactory }

        setContent {
            SpaceCompanyTheme {
                MainScreen(gameViewModel = gameViewModel)
            }
        }
    }
}