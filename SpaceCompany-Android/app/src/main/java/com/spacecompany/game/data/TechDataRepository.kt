package com.spacecompany.game.data

import com.spacecompany.game.model.Resource
import com.spacecompany.game.model.TechType

data class TechData(
    val id: String,
    val name: String,
    val desc: String,
    val type: TechType,
    val cost: Map<Resource, Double>,
    val maxLevel: Int = 1,
    val unlocked: Boolean = false,
    val newResources: List<String> = emptyList(),
    val newTabs: List<String> = emptyList(),
    val newTechs: List<String> = emptyList()
    // `onApply` effects will be handled in the ViewModel
)

object TechDataRepository {
    val techs: Map<String, TechData> = mapOf(
        "unlockStorage" to TechData("unlockStorage", "Storage Upgrades", "This will allow you to build storage upgrades to increase the maximum on the amount of resource you can have at once.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 5.0), unlocked = true, newTechs = listOf("unlockOil")),
        "unlockBasicEnergy" to TechData("unlockBasicEnergy", "Basic Energy Production", "You will be able to produce power from steam engines using Charcoal made from wood in a furnace.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 20.0), unlocked = true, newResources = listOf("energyNav", "charcoalNav"), newTechs = listOf("unlockSolar", "unlockMachines", "upgradeEngineTech")),
        "unlockOil" to TechData("unlockOil", "Oil Processing", "Oil used to fuel more advanced machines that gather resources and also to produce more power than basic means.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 30.0), newResources = listOf("oilNav")),
        "unlockSolar" to TechData("unlockSolar", "Solar Panels", "Solar Panels produce Energy without the need for fuel, but they do it slower than other forms of Energy production.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 50.0), newTechs = listOf("upgradeSolarTech")),
        "unlockMachines" to TechData("unlockMachines", "Resource Machines", "Resource Machines produce more resources than simple methods but require a constant supply of power to work.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 100.0), newTechs = listOf("unlockSolarSystem", "upgradeResourceTech", "unlockDestruction")),
        "unlockDestruction" to TechData("unlockDestruction", "Destruction of Machines", "This allows you to destroy machines you have already created.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 500.0)),
        "unlockSolarSystem" to TechData("unlockSolarSystem", "Space", "Unlocking space-travel allows for launching of rockets and opens a whole new field of research.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 500.0), newTabs = listOf("solarSystemTab"), newTechs = listOf("unlockLabT2", "unlockRocketFuelT2")),
        "unlockRocketFuelT2" to TechData("unlockRocketFuelT2", "Oxidisation", "Oxidisation is a more efficient process of creating Rocket Fuel.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 450000.0), newResources = listOf("rocketFuelT2"), newTechs = listOf("unlockRocketFuelT3")),
        "unlockRocketFuelT3" to TechData("unlockRocketFuelT3", "Hydrazine", "Hydrazine is a compound created by Methane that increases the speed at which rocket fuel can be produced.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 3200000.0), newResources = listOf("rocketFuelT3")),
        "unlockLabT2" to TechData("unlockLabT2", "Tier 2 Science", "Researching this will allow you to increase your science production drastically.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 500.0), newTechs = listOf("unlockLabT3")),
        "unlockLabT3" to TechData("unlockLabT3", "Tier 3 Science", "Researching this will allow you to access the third tier of science production.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 3000.0), newTechs = listOf("unlockLabT4")),
        "unlockLabT4" to TechData("unlockLabT4", "Tier 4 Science", "Researching this will allow you to access the fourth tier of science production.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 50000000.0)),
        "unlockBatteries" to TechData("unlockBatteries", "Tier 1 Batteries", "Tier 1 Batteries improve the amount of energy you can store at once.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 15000.0), newResources = listOf("batteries", "energyStorageBox"), newTechs = listOf("unlockBatteriesT2")),
        "unlockBatteriesT2" to TechData("unlockBatteriesT2", "Tier 2 Batteries", "Tier 2 Batteries improve the amount of energy you can store at once", TechType.UNLOCK, mapOf(Resource.SCIENCE to 300000.0), newResources = listOf("batteriesT2"), newTechs = listOf("unlockBatteriesT3")),
        "unlockBatteriesT3" to TechData("unlockBatteriesT3", "Tier 3 Batteries", "Tier 3 Batteries improve the amount of energy you can store at once", TechType.UNLOCK, mapOf(Resource.SCIENCE to 3000000.0), newResources = listOf("batteriesT3"), newTechs = listOf("unlockBatteriesT4")),
        "unlockBatteriesT4" to TechData("unlockBatteriesT4", "Tier 4 Batteries", "Tier 4 Batteries improve the amount of energy you can store at once", TechType.UNLOCK, mapOf(Resource.SCIENCE to 30000000.0), newResources = listOf("batteriesT4")),
        "unlockPlasma" to TechData("unlockPlasma", "Plasma Tier 1 Technology", "This allows you to turn your energy and hydrogen into Plasma", TechType.UNLOCK, mapOf(Resource.SCIENCE to 40000.0), newResources = listOf("plasmaNav"), newTechs = listOf("unlockPlasmaTier2")),
        "unlockPlasmaTier2" to TechData("unlockPlasmaTier2", "Plasma Tier 2 Technology", "This research unlocks the second tier of Plasma production, the Plasmatic Pit", TechType.UNLOCK, mapOf(Resource.SCIENCE to 60000.0), newResources = listOf("plasmaTier2")),
        "unlockPSU" to TechData("unlockPSU", "Plasma Storage Units", "PSUs increase the limit on plasma you can store at once.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 9500000.0), newResources = listOf("plasmaStorageUnits", "plasmaStorageBox"), newTechs = listOf("unlockPSUT2")),
        "unlockPSUT2" to TechData("unlockPSUT2", "Tier 2 Plasma Storage Units", "Tier 2 PSUs are more efficient at storing plasma.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 37000000.0), newResources = listOf("plasmaStorageUnitsT2")),
        "unlockEmc" to TechData("unlockEmc", "Energy-Mass Conversion", "This power technology not only lets you create existing resources, but allows you to make new, and only creatable elements.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 60000.0), newResources = listOf("emcPage"), newTechs = listOf("unlockMeteorite")),
        "unlockMeteorite" to TechData("unlockMeteorite", "Meteorite", "All of the pre-existing Meteorite that once was in the Kuiper Belt has all been mined away. Now, the only way to get is to make it in machines from energy.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 100000.0), newResources = listOf("meteoriteNav", "meteoriteEMC"), newTechs = listOf("unlockMeteoriteTier1")),
        "unlockMeteoriteTier1" to TechData("unlockMeteoriteTier1", "Meteorite Tier 1", "Research an automated way to gather Meteorite.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 75000.0), newResources = listOf("meteoriteTier1"), newTechs = listOf("unlockMeteoriteTier2")),
        "unlockMeteoriteTier2" to TechData("unlockMeteoriteTier2", "Meteorite Tier 2", "Research a more efficient method of getting meteorite than creating it artificially.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 100000.0), newResources = listOf("meteoriteTier2")),
        "unlockDyson" to TechData("unlockDyson", "Dyson Ring", "Dyson Rings produce huge amounts of energy by surrounding the sun in solar stations.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 100000.0), newResources = listOf("dysonPage"), newTechs = listOf("unlockDysonSphere")),
        "unlockDysonSphere" to TechData("unlockDysonSphere", "Dyson Swarms and Spheres", "The Dyson Swarms encapsulate the sun in rings of solar stations, whereas Spheres completely encompasses it.", TechType.UNLOCK, mapOf(Resource.SCIENCE to 500000.0), newResources = listOf("dysonSphere")),

        // Upgrades
        "upgradeResourceTech" to TechData("upgradeResourceTech", "Upgrade Resource Technology", "Make your resource machines produce even more resources than before.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 300.0)),
        "upgradeEngineTech" to TechData("upgradeEngineTech", "Upgrade Engine Technology", "Upgrading Engine Technology will make Charcoal engines produce 4 Energy per second instead of 2.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 1000.0)),
        "upgradeSolarTech" to TechData("upgradeSolarTech", "Upgrade Solar Technology", "Upgrading Solar Technology will make solar panels produce 3 Energy per second instead of 1.5.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 5000.0), newTechs = listOf("unlockBatteries")),
        "efficiencyResearch" to TechData("efficiencyResearch", "Resource Efficiency", "Resource Efficiency increases the income of resources by 1%/s per purchase.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 100000.0), maxLevel = -1),
        "scienceEfficiencyResearch" to TechData("scienceEfficiencyResearch", "Science Efficiency", "Science Efficiency increases the science production by 2% per purchase.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 10000000.0), maxLevel = -1),
        "energyEfficiencyResearch" to TechData("energyEfficiencyResearch", "Energy Efficiency", "Energy Efficiency decreases the energy consumption of all machines by 1%/s per purchase.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 10000000.0), maxLevel = 25),
        "batteryEfficiencyResearch" to TechData("batteryEfficiencyResearch", "Battery Efficiency", "Battery Efficiency improves the storage capabilities of your batteries by 1% per upgrade.", TechType.UPGRADE, mapOf(Resource.SCIENCE to 100000000.0), maxLevel = 200)
    )
}
