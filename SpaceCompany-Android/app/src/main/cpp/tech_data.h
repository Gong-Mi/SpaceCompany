#ifndef SPACECOMPANY_TECH_DATA_H
#define SPACECOMPANY_TECH_DATA_H

#include "game_state.h"
#include "game_data.h" // For Cost struct

#define MAX_TECH_COSTS 3
#define MAX_UNLOCKS 5

typedef enum {
    TECH_UNLOCK,
    TECH_UPGRADE
} TechType;

typedef struct {
    const char* id;
    const char* name;
    TechType type;
    Cost cost[MAX_TECH_COSTS];
    int num_cost;
    int maxLevel; // -1 for unlimited
    
    // Effects
    const char* unlocks_techs[MAX_UNLOCKS];
    int num_unlocks_techs;
    Resource unlocks_resources[MAX_UNLOCKS];
    int num_unlocks_resources;
    
    // Special modifier effect (simplified for now)
    const char* modifier_key;
    double modifier_value;

} TechData;

typedef struct {
    TechData techs[100];
    int num_techs;
} TechGameData;

extern TechGameData tech_game_data;

void initialize_tech_data();

#endif //SPACECOMPANY_TECH_DATA_H
