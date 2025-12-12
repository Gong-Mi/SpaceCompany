package com.spacecompany.game.model

import kotlinx.serialization.Serializable

@Serializable
data class TechState(
    val id: String,
    var current: Int = 0,
    var unlocked: Boolean = false
)
