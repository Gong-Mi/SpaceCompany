package com.spacecompany.game.model

import kotlinx.serialization.Serializable

@Serializable
data class BuildingState(
    val id: String, // Corresponds to variableId from buildingData.js
    var count: Long = 0
)
