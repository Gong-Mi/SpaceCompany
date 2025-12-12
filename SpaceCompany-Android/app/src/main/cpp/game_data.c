#include "game_data.h"
#include <stddef.h>

GameData game_data;

void initialize_game_data() {
    game_data.num_buildings = 26; // Example, update with actual count

    int i = 0;
    // Metal
    game_data.buildings[i++] = (BuildingData){"miner", "Miner", METAL, {{METAL, 1.0}}, 1, {{METAL, 10.0}, {WOOD, 5.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"heavyDrill", "Heavy Drill", METAL, {{ENERGY, -2.0}, {METAL, 8.0}}, 2, {{METAL, 160.0}, {GEM, 60.0}, {OIL, 50.0}}, 3};
    // ... add all other buildings
    
    // Gem
    game_data.buildings[i++] = (BuildingData){"gemMiner", "Gem Miner", GEM, {{GEM, 1.0}}, 1, {{METAL, 15.0}, {GEM, 10.0}}, 2};

    // Wood
    game_data.buildings[i++] = (BuildingData){"woodcutter", "Woodcutter", WOOD, {{WOOD, 1.0}}, 1, {{METAL, 10.0}, {WOOD, 5.0}}, 2};

    // Oil
    game_data.buildings[i++] = (BuildingData){"pump", "Small Pump", OIL, {{OIL, 1.0}}, 1, {{METAL, 60.0}, {GEM, 20.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"pumpjack", "Pumpjack", OIL, {{ENERGY, -4.0}, {OIL, 10.0}}, 2, {{METAL, 250.0}, {GEM, 80.0}, {OIL, 50.0}}, 3};

    // Charcoal
    game_data.buildings[i++] = (BuildingData){"woodburner", "Woodburner", CHARCOAL, {{WOOD, -2.0}, {CHARCOAL, 1.0}}, 2, {{METAL, 10.0}, {WOOD, 5.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"furnace", "Furnace", CHARCOAL, {{ENERGY, -3.0}, {WOOD, -6.0}, {CHARCOAL, 4.0}}, 3, {{METAL, 80.0}, {WOOD, 40.0}, {OIL, 100.0}}, 3};
    
    // Silicon
    game_data.buildings[i++] = (BuildingData){"blowtorch", "Empowered Blowtorch", SILICON, {{SILICON, 1.0}}, 1, {{LUNARITE, 150.0}, {TITANIUM, 30.0}}, 2};

    // Energy Producers
    game_data.buildings[i++] = (BuildingData){"charcoalEngine", "Charcoal Engine", ENERGY, {{ENERGY, 2.0}, {CHARCOAL, -1.0}}, 2, {{METAL, 50.0}, {GEM, 25.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"solarPanel", "Solar Panel", ENERGY, {{ENERGY, 1.5}}, 1, {{METAL, 30.0}, {GEM, 35.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"methaneStation", "Methane Power Station", ENERGY, {{ENERGY, 23.0}, {METHANE, -6.0}}, 2, {{LUNARITE, 110.0}, {TITANIUM, 90.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"nuclearStation", "Nuclear Power Station", ENERGY, {{ENERGY, 153.0}, {URANIUM, -7.0}}, 2, {{LUNARITE, 20000.0}, {TITANIUM, 10000.0}}, 2};
    game_data.buildings[i++] = (BuildingData){"magmatic", "Magmatic Dynamo", ENERGY, {{ENERGY, 191.0}, {LAVA, -11.0}}, 2, {{LUNARITE, 25000.0}, {GEM, 30000.0}, {SILVER, 20000.0}}, 3};
    game_data.buildings[i++] = (BuildingData){"fusionReactor", "Fusion Reactor", ENERGY, {{ENERGY, 273.0}, {HYDROGEN, -10.0}, {HELIUM, -10.0}}, 3, {{LUNARITE, 30000.0}, {TITANIUM, 20000.0}, {SILICON, 15000.0}}, 3};

    // Labs for science
    game_data.buildings[i++] = (BuildingData){"lab", "Home Science Kit", SCIENCE, {{SCIENCE, 0.1}}, 1, {{METAL, 20.0}, {GEM, 15.0}, {WOOD, 10.0}}, 3};
    game_data.buildings[i++] = (BuildingData){"labT2", "High School Science", SCIENCE, {{SCIENCE, 1.0}}, 1, {{METAL, 1000.0}, {GEM, 200.0}, {WOOD, 500.0}}, 3};

    // Rocket Fuel
    game_data.buildings[i++] = (BuildingData){"chemicalPlant", "Chemical Plant", ROCKETFUEL, {{ROCKETFUEL, 0.2}}, 1, {{METAL, 1000.0}, {GEM, 750.0}, {OIL, 500.0}}, 3};

    game_data.num_buildings = i;
}
