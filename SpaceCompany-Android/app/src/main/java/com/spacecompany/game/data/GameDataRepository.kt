package com.spacecompany.game.data

import com.spacecompany.game.model.BuildingType
import com.spacecompany.game.model.Resource

// In a real application, this data would be loaded from a file (e.g., JSON, XML)
// or a remote server. For this rewrite, we are manually translating it from the JS files.

data class BuildingData(
    val id: String, // e.g., "miner"
    val name: String,
    val type: BuildingType,
    val primaryResource: Resource,
    val resourcePerSecond: Map<Resource, Double>,
    val cost: Map<Resource, Double>
)

object GameDataRepository {
    val buildings: Map<String, BuildingData> = mapOf(
        // Metal
        "miner" to BuildingData("miner", "Miner", BuildingType.PRODUCER, Resource.METAL, mapOf(Resource.METAL to 1.0), mapOf(Resource.METAL to 10.0, Resource.WOOD to 5.0)),
        "heavyDrill" to BuildingData("heavyDrill", "Heavy Drill", BuildingType.PRODUCER, Resource.METAL, mapOf(Resource.ENERGY to -2.0, Resource.METAL to 8.0), mapOf(Resource.METAL to 160.0, Resource.GEM to 60.0, Resource.OIL to 50.0)),
        "gigaDrill" to BuildingData("gigaDrill", "Giga Drill", BuildingType.PRODUCER, Resource.METAL, mapOf(Resource.ENERGY to -9.0, Resource.METAL to 108.0), mapOf(Resource.LUNARITE to 2800.0, Resource.GEM to 3400.0, Resource.SILICON to 4100.0)),
        "quantumDrill" to BuildingData("quantumDrill", "Quantum Drill", BuildingType.PRODUCER, Resource.METAL, mapOf(Resource.ENERGY to -24.0, Resource.METAL to 427.0), mapOf(Resource.LUNARITE to 29000.0, Resource.GOLD to 18700.0, Resource.METEORITE to 900.0)),
        "multiDrill" to BuildingData("multiDrill", "Multiverse Drill", BuildingType.PRODUCER, Resource.METAL, mapOf(Resource.ENERGY to -131.0, Resource.METAL to 4768.0), mapOf(Resource.TITANIUM to 184000.0, Resource.GOLD to 133000.0, Resource.OIL to 170000.0)),

        // Gem
        "gemMiner" to BuildingData("gemMiner", "Gem Miner", BuildingType.PRODUCER, Resource.GEM, mapOf(Resource.GEM to 1.0), mapOf(Resource.METAL to 15.0, Resource.GEM to 10.0)),
        "advancedDrill" to BuildingData("advancedDrill", "Advanced Drill", BuildingType.PRODUCER, Resource.GEM, mapOf(Resource.ENERGY to -2.0, Resource.GEM to 4.0), mapOf(Resource.METAL to 120.0, Resource.GEM to 200.0, Resource.OIL to 60.0)),
        "diamondDrill" to BuildingData("diamondDrill", "Diamond Drill", BuildingType.PRODUCER, Resource.GEM, mapOf(Resource.ENERGY to -15.0, Resource.GEM to 89.0), mapOf(Resource.LUNARITE to 3400.0, Resource.GEM to 8000.0, Resource.SILICON to 4500.0)),
        "carbyneDrill" to BuildingData("carbyneDrill", "Carbyne Drill", BuildingType.PRODUCER, Resource.GEM, mapOf(Resource.ENERGY to -40.0, Resource.GEM to 358.0), mapOf(Resource.LUNARITE to 21000.0, Resource.GEM to 27000.0, Resource.METEORITE to 800.0)),
        "diamondChamber" to BuildingData("diamondChamber", "Diamond Accretion Chamber", BuildingType.PRODUCER, Resource.GEM, mapOf(Resource.ENERGY to -260.0, Resource.GEM to 3747.0), mapOf(Resource.URANIUM to 181000.0, Resource.CHARCOAL to 185000.0, Resource.METEORITE to 12500.0)),


        // Wood
        "woodcutter" to BuildingData("woodcutter", "Woodcutter", BuildingType.PRODUCER, Resource.WOOD, mapOf(Resource.WOOD to 1.0), mapOf(Resource.METAL to 10.0, Resource.WOOD to 5.0)),
        "laserCutter" to BuildingData("laserCutter", "Laser Cutter", BuildingType.PRODUCER, Resource.WOOD, mapOf(Resource.ENERGY to -4.0, Resource.WOOD to 6.0), mapOf(Resource.METAL to 50.0, Resource.GEM to 90.0, Resource.OIL to 40.0)),
        "deforester" to BuildingData("deforester", "Mass Deforester", BuildingType.PRODUCER, Resource.WOOD, mapOf(Resource.ENERGY to -16.0, Resource.WOOD to 74.0), mapOf(Resource.LUNARITE to 3000.0, Resource.TITANIUM to 2700.0, Resource.SILICON to 2500.0)),
        "infuser" to BuildingData("infuser", "Biomass Infuser", BuildingType.PRODUCER, Resource.WOOD, mapOf(Resource.ENERGY to -43.0, Resource.WOOD to 297.0), mapOf(Resource.LUNARITE to 16000.0, Resource.OIL to 31200.0, Resource.METEORITE to 490.0)),
        "forest" to BuildingData("forest", "Russian Forest", BuildingType.PRODUCER, Resource.WOOD, mapOf(Resource.ENERGY to -244.0, Resource.WOOD to 3278.0), mapOf(Resource.METAL to 122000.0, Resource.GEM to 151000.0, Resource.HYDROGEN to 183000.0)),

        // Oil
        "pump" to BuildingData("pump", "Small Pump", BuildingType.PRODUCER, Resource.OIL, mapOf(Resource.OIL to 1.0), mapOf(Resource.METAL to 60.0, Resource.GEM to 20.0)),
        "pumpjack" to BuildingData("pumpjack", "Pumpjack", BuildingType.PRODUCER, Resource.OIL, mapOf(Resource.ENERGY to -4.0, Resource.OIL to 10.0), mapOf(Resource.METAL to 250.0, Resource.GEM to 80.0, Resource.OIL to 50.0)),
        "oilField" to BuildingData("oilField", "Oil Field", BuildingType.PRODUCER, Resource.OIL, mapOf(Resource.ENERGY to -12.0, Resource.OIL to 63.0), mapOf(Resource.LUNARITE to 2400.0, Resource.TITANIUM to 2700.0, Resource.SILICON to 3900.0)),
        "oilRig" to BuildingData("oilRig", "Offshore Rig", BuildingType.PRODUCER, Resource.OIL, mapOf(Resource.ENERGY to -44.0, Resource.OIL to 246.0), mapOf(Resource.LUNARITE to 19400.0, Resource.TITANIUM to 16800.0, Resource.METEORITE to 760.0)),
        "fossilator" to BuildingData("fossilator", "Fossilator 9000", BuildingType.PRODUCER, Resource.OIL, mapOf(Resource.ENERGY to -258.0, Resource.OIL to 2627.0), mapOf(Resource.URANIUM to 110000.0, Resource.CHARCOAL to 96000.0, Resource.LAVA to 167000.0)),

        // Charcoal
        "woodburner" to BuildingData("woodburner", "Woodburner", BuildingType.PRODUCER, Resource.CHARCOAL, mapOf(Resource.WOOD to -2.0, Resource.CHARCOAL to 1.0), mapOf(Resource.METAL to 10.0, Resource.WOOD to 5.0)),
        "furnace" to BuildingData("furnace", "Furnace", BuildingType.PRODUCER, Resource.CHARCOAL, mapOf(Resource.ENERGY to -3.0, Resource.WOOD to -6.0, Resource.CHARCOAL to 4.0), mapOf(Resource.METAL to 80.0, Resource.WOOD to 40.0, Resource.OIL to 100.0)),
        "kiln" to BuildingData("kiln", "Industrial Kiln", BuildingType.PRODUCER, Resource.CHARCOAL, mapOf(Resource.ENERGY to -13.0, Resource.WOOD to -56.0, Resource.CHARCOAL to 53.0), mapOf(Resource.LUNARITE to 3500.0, Resource.GEM to 6200.0, Resource.SILICON to 3800.0)),
        "fryer" to BuildingData("fryer", "Forest Fryer", BuildingType.PRODUCER, Resource.CHARCOAL, mapOf(Resource.ENERGY to -34.0, Resource.WOOD to -148.0, Resource.CHARCOAL to 210.0), mapOf(Resource.LUNARITE to 15800.0, Resource.LAVA to 12500.0, Resource.METEORITE to 560.0)),
        "microPollutor" to BuildingData("microPollutor", "Microverse Pollutor", BuildingType.PRODUCER, Resource.CHARCOAL, mapOf(Resource.ENERGY to -187.0, Resource.WOOD to -950.0, Resource.CHARCOAL to 2267.0), mapOf(Resource.METAL to 133000.0, Resource.WOOD to 189000.0, Resource.LAVA to 160000.0)),

        // Silicon
        "blowtorch" to BuildingData("blowtorch", "Empowered Blowtorch", BuildingType.PRODUCER, Resource.SILICON, mapOf(Resource.SILICON to 1.0), mapOf(Resource.LUNARITE to 150.0, Resource.TITANIUM to 30.0)),
        "scorcher" to BuildingData("scorcher", "Seaside Scorcher", BuildingType.PRODUCER, Resource.SILICON, mapOf(Resource.ENERGY to -18.0, Resource.SILICON to 9.0), mapOf(Resource.LUNARITE to 500.0, Resource.GEM to 1200.0, Resource.OIL to 1600.0)),
        "annihilator" to BuildingData("annihilator", "Beach Annihilator", BuildingType.PRODUCER, Resource.SILICON, mapOf(Resource.ENERGY to -53.0, Resource.SILICON to 40.0), mapOf(Resource.LUNARITE to 3000.0, Resource.GEM to 8300.0, Resource.SILVER to 2400.0)),
        "desert" to BuildingData("desert", "Desert Destroyer", BuildingType.PRODUCER, Resource.SILICON, mapOf(Resource.ENERGY to -138.0, Resource.SILICON to 157.0), mapOf(Resource.LUNARITE to 20000.0, Resource.SILICON to 17700.0, Resource.METEORITE to 400.0)),
        "tardis" to BuildingData("tardis", "T.A.R.D.I.S.", BuildingType.PRODUCER, Resource.SILICON, mapOf(Resource.ENERGY to -746.0, Resource.SILICON to 1487.0), mapOf(Resource.TITANIUM to 204000.0, Resource.SILICON to 205000.0, Resource.METEORITE to 17800.0)),

        // Energy Producers
        "charcoalEngine" to BuildingData("charcoalEngine", "Charcoal Engine", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 2.0, Resource.CHARCOAL to -1.0), mapOf(Resource.METAL to 50.0, Resource.GEM to 25.0)),
        "solarPanel" to BuildingData("solarPanel", "Solar Panel", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 1.5), mapOf(Resource.METAL to 30.0, Resource.GEM to 35.0)),
        "methaneStation" to BuildingData("methaneStation", "Methane Power Station", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 23.0, Resource.METHANE to -6.0), mapOf(Resource.LUNARITE to 110.0, Resource.TITANIUM to 90.0)),
        "nuclearStation" to BuildingData("nuclearStation", "Nuclear Power Station", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 153.0, Resource.URANIUM to -7.0), mapOf(Resource.LUNARITE to 20000.0, Resource.TITANIUM to 10000.0)),
        "magmatic" to BuildingData("magmatic", "Magmatic Dynamo", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 191.0, Resource.LAVA to -11.0), mapOf(Resource.LUNARITE to 25000.0, Resource.GEM to 30000.0, Resource.SILVER to 20000.0)),
        "fusionReactor" to BuildingData("fusionReactor", "Fusion Reactor", BuildingType.PRODUCER, Resource.ENERGY, mapOf(Resource.ENERGY to 273.0, Resource.HYDROGEN to -10.0, Resource.HELIUM to -10.0), mapOf(Resource.LUNARITE to 30000.0, Resource.TITANIUM to 20000.0, Resource.SILICON to 15000.0))

        // ... and all other buildings from the JS file would go here
    )
}

