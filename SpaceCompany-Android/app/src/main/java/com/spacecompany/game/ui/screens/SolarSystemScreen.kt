package com.spacecompany.game.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spacecompany.game.data.PlanetDataRepository
import com.spacecompany.game.model.GameState
import java.text.DecimalFormat

@Composable
fun SolarSystemScreen(
    gameState: GameState,
    onBuildRocket: () -> Unit,
    onExplorePlanet: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!gameState.rocketIsBuilt) {
            val metalCost = 1200.0
            val gemCost = 900.0
            val oilCost = 1000.0
            val canAfford = (gameState.resources[Resource.METAL]?.current ?: 0.0) >= metalCost &&
                           (gameState.resources[Resource.GEM]?.current ?: 0.0) >= gemCost &&
                           (gameState.resources[Resource.OIL]?.current ?: 0.0) >= oilCost

            Text("You need to build a rocket to explore the solar system.")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onBuildRocket, enabled = canAfford) {
                Text("Build Rocket (1200 Metal, 900 Gem, 1000 Oil)")
            }
        } else {
            PlanetList(gameState = gameState, onExplorePlanet = onExplorePlanet)
        }
    }
}

@Composable
private fun PlanetList(gameState: GameState, onExplorePlanet: (String) -> Unit) {
    val df = DecimalFormat("#,##0")
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(PlanetDataRepository.planets.values.toList()) { planetData ->
            val planetState = gameState.planets[planetData.id]!!
            Column {
                Text(planetData.name, style = MaterialTheme.typography.titleLarge)
                Text(planetData.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                if (planetState.isExplored) {
                    Text("Explored!")
                } else {
                    val cost = planetData.explorationCost
                    val costString = cost.map { (res, amount) -> "${df.format(amount)} ${res.name}" }.joinToString()
                    val canAfford = cost.all { (res, amount) ->
                        (gameState.resources[res]?.current ?: 0.0) >= amount
                    }
                    Button(onClick = { onExplorePlanet(planetData.id) }, enabled = canAfford) {
                        Text("Explore ($costString)")
                    }
                }
            }
        }
    }
}
