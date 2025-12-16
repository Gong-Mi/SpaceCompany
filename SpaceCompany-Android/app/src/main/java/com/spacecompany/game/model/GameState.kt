package com.spacecompany.game.model

// This data class will hold the entire state of the game.
// It mirrors the global variables found in variable.js.
import com.spacecompany.game.data.GameDataRepository
import com.spacecompany.game.data.PlanetDataRepository
import com.spacecompany.game.data.TechDataRepository
import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    // Company
    val companyName: String = "Space Company",

    val resources: Map<Resource, ResourceState> = Resource.values().associateWith {
        ResourceState(
            id = it,
            capacity = 50.0,
            current = when (it) {
                Resource.METAL -> 100.0
                Resource.WOOD -> 50.0
                else -> 0.0
            }
        )
    },

    val buildings: Map<String, BuildingState> = GameDataRepository.buildings.keys.associateWith { BuildingState(id = it) },

    val techs: Map<String, TechState> = TechDataRepository.techs.keys.associateWith { TechState(id = it) },

    val planets: Map<String, PlanetState> = PlanetDataRepository.planets.keys.associateWith { PlanetState(id = it) },

    val modifiers: Map<String, Double> = emptyMap(),

    // Flags
    val rocketIsBuilt: Boolean = false,
    val rocketLaunched: Boolean = false,
    val researchUnlocked: Boolean = false,
    val globalEnergyLock: Boolean = false,

    // UI State
    val selectedResource: Resource? = Resource.METAL
) {
    @kotlinx.serialization.Transient
    val allResources: List<ResourceState>
        get() = resources.values.toList()
}

// Represents the dynamic state of a single resource.
@Serializable
data class ResourceState(
    val id: Resource,
    val desc: String = "",
    var current: Double = 0.0,
    var perSecond: Double = 0.0,
    var capacity: Double,
    var unlocked: Boolean = false
)
