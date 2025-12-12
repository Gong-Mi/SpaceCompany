package com.spacecompany.game.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.spacecompany.game.data.TechDataRepository
import com.spacecompany.game.model.GameState
import com.spacecompany.game.ui.components.TechRow

@Composable
fun ResearchScreen(
    gameState: GameState,
    onBuyTech: (String) -> Unit
) {
    val techs = TechDataRepository.techs.values.filter {
        gameState.techs[it.id]?.unlocked == true
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(techs) { techData ->
            val techState = gameState.techs[techData.id]!!
            val canAfford = techData.cost.all { (resource, cost) ->
                (gameState.resources[resource]?.current ?: 0.0) >= cost
            }
            TechRow(
                techData = techData,
                techState = techState,
                canAfford = canAfford,
                onBuy = { onBuyTech(techData.id) }
            )
        }
    }
}
