package com.spacecompany.game.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spacecompany.game.ui.components.GameTabs
import com.spacecompany.game.ui.components.GameTopAppBar
import com.spacecompany.game.ui.components.ResourceDetailView
import com.spacecompany.game.ui.components.ResourcePanel
import com.spacecompany.game.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(gameViewModel: GameViewModel = viewModel()) {
    val gameState by gameViewModel.gameState.collectAsStateWithLifecycle()
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            GameTopAppBar(
                title = gameState.companyName,
                isEnergyLow = gameState.globalEnergyLock
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            GameTabs(
                selectedIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )

            when (selectedTabIndex) {
                0 -> { // Resources
                    Row {
                        ResourcePanel(
                            modifier = Modifier.weight(0.4f),
                            resources = gameState.allResources,
                            onResourceSelected = gameViewModel::onResourceSelected
                        )
                        ResourceDetailView(
                            modifier = Modifier.weight(0.6f),
                            gameState = gameState,
                            getCost = { base, count -> gameViewModel.getCost(base, count) },
                            onBuyBuilding = gameViewModel::buyBuilding
                        )
                    }
                }
                1 -> { // Research
                    ResearchScreen(
                        gameState = gameState,
                        onBuyTech = gameViewModel::buyTech
                    )
                }
                2 -> { // Settings
                    SettingsScreen(
                        onSave = gameViewModel::manualSave,
                        onDelete = gameViewModel::deleteSave
                    )
                }
                3 -> { // Solar System
                    SolarSystemScreen(
                        gameState = gameState,
                        onBuildRocket = gameViewModel::buildRocket,
                        onExplorePlanet = gameViewModel::explorePlanet
                    )
                }
                // Other tabs go here
            }
        }
    }
}
