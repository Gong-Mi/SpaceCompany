package com.spacecompany.game.model

// Represents all distinct resources in the game
enum class Resource {
    ENERGY, PLASMA, URANIUM, LAVA, OIL, METAL, GEM, CHARCOAL, WOOD, SILICON,
    LUNARITE, METHANE, TITANIUM, GOLD, SILVER, HYDROGEN, HELIUM, ICE, METEORITE, SCIENCE, ROCKETFUEL;

    // Helper to convert string IDs to enum, case-insensitive
    companion object {
        fun fromString(id: String): Resource? {
            return entries.find { it.name.equals(id, ignoreCase = true) }
        }
    }
}
