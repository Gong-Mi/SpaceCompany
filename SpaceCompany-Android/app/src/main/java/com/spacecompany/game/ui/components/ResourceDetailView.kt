package com.spacecompany.game.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spacecompany.game.data.GameDataRepository
import com.spacecompany.game.model.GameState
import com.spacecompany.game.model.Resource

@Composable
fun ResourceDetailView(
    modifier: Modifier = Modifier,
    gameState: GameState,
    getCost: (Double, Long) -> Double,
    onBuyBuilding: (String) -> Unit
) {
    val selectedResource = gameState.resources[gameState.selectedResource]

    if (selectedResource == null) {
        Text(
            modifier = modifier.padding(16.dp),
            text = "Select a resource from the left."
        )
        return
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = selectedResource.id.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = selectedResource.desc, style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        Text(text = "Buildings", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        // Find all buildings that produce the selected resource
        val relevantBuildings = GameDataRepository.buildings.values.filter {
            it.primaryResource == selectedResource.id
        }

        if (relevantBuildings.isEmpty()) {
            Text("No buildings produce this resource directly.")
        } else {
            relevantBuildings.forEach { buildingData ->
                val buildingState = gameState.buildings[buildingData.id]
                if (buildingState != null) {
                    val cost = buildingData.cost.mapValues { (_, baseCost) ->
                        getCost(baseCost, buildingState.count)
                    }
                    val canAfford = cost.all { (resource, costValue) ->
                        (gameState.resources[resource]?.current ?: 0.0) >= costValue
                    }

                    BuildingRow(
                        building = buildingState,
                        cost = cost,
                        isAffordable = canAfford,
                        onBuy = { onBuyBuilding(buildingData.id) }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
