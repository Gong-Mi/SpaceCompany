#include "tech_data.h"
#include <stddef.h>

TechGameData tech_game_data;

void initialize_tech_data() {
    int i = 0;

    // Unlock Storage
    tech_game_data.techs[i++] = (TechData){
        .id = "unlockStorage",
        .name = "Storage Upgrades",
        .type = TECH_UNLOCK,
        .cost = {{SCIENCE, 5.0}},
        .num_cost = 1,
        .maxLevel = 1,
        .unlocks_techs = {"unlockOil"},
        .num_unlocks_techs = 1
    };

    // Unlock Basic Energy
    tech_game_data.techs[i++] = (TechData){
        .id = "unlockBasicEnergy",
        .name = "Basic Energy Production",
        .type = TECH_UNLOCK,
        .cost = {{SCIENCE, 20.0}},
        .num_cost = 1,
        .maxLevel = 1,
        .unlocks_techs = {"unlockSolar", "unlockMachines", "upgradeEngineTech"},
        .num_unlocks_techs = 3,
        .unlocks_resources = {ENERGY, CHARCOAL},
        .num_unlocks_resources = 2
    };

    // Unlock Oil
    tech_game_data.techs[i++] = (TechData){
        .id = "unlockOil",
        .name = "Oil Processing",
        .type = TECH_UNLOCK,
        .cost = {{SCIENCE, 30.0}},
        .num_cost = 1,
        .maxLevel = 1,
        .unlocks_resources = {OIL},
        .num_unlocks_resources = 1
    };
    
    // Unlock Solar
    tech_game_data.techs[i++] = (TechData){
        .id = "unlockSolar",
        .name = "Solar Panels",
        .type = TECH_UNLOCK,
        .cost = {{SCIENCE, 50.0}},
        .num_cost = 1,
        .maxLevel = 1,
        .unlocks_techs = {"upgradeSolarTech"},
        .num_unlocks_techs = 1
    };

    // Upgrade Engine Tech
    tech_game_data.techs[i++] = (TechData){
        .id = "upgradeEngineTech",
        .name = "Upgrade Engine Technology",
        .type = TECH_UPGRADE,
        .cost = {{SCIENCE, 1000.0}},
        .num_cost = 1,
        .maxLevel = 1,
        .modifier_key = "charcoalEngineOutput",
        .modifier_value = 4.0
    };

    // ... add more techs as needed

    tech_game_data.num_techs = i;
}
