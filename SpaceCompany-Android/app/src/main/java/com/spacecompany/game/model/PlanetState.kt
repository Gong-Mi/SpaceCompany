package com.spacecompany.game.model

import kotlinx.serialization.Serializable

@Serializable
data class PlanetState(
    val id: String,
    var isExplored: Boolean = false
)
