package com.spacecompany.game.data

import com.spacecompany.game.model.Resource

data class PlanetData(
    val id: String,
    val name: String,
    val description: String,
    val explorationCost: Map<Resource, Double>
)

object PlanetDataRepository {
    val planets: Map<String, PlanetData> = mapOf(
        "moon" to PlanetData(
            id = "moon",
            name = "The Moon",
            description = "The Moon is our largest satellite... It contains a large quantity of Lunarite.",
            explorationCost = mapOf(Resource.ROCKETFUEL to 20.0)
        ),
        "venus" to PlanetData(
            id = "venus",
            name = "Venus",
            description = "Venus is the hottest planet in the solar system... You can extract methane from Venus.",
            explorationCost = mapOf(Resource.ROCKETFUEL to 50.0)
        ),
        "mars" to PlanetData(
            id = "mars",
            name = "Mars",
            description = "Mars is the second closest planet to Earth... You can find Titanium on the surface.",
            explorationCost = mapOf(Resource.ROCKETFUEL to 80.0)
        )
        // Other planets will be added here
    )
}
