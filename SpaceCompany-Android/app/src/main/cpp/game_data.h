#ifndef SPACECOMPANY_GAME_DATA_H
#define SPACECOMPANY_GAME_DATA_H

#include "game_state.h"

#define MAX_BUILDING_INPUTS 3
#define MAX_BUILDING_COSTS 3

typedef struct {
    Resource resource;
    double amount;
} Cost;

typedef struct {
    Resource resource;
    double rate;
} Production;

typedef struct {
    const char* id;
    const char* name;
    Resource primaryResource;
    Production production[MAX_BUILDING_INPUTS];
    int num_production;
    Cost cost[MAX_BUILDING_COSTS];
    int num_cost;
} BuildingData;

typedef struct {
    BuildingData buildings[100];
    int num_buildings;
} GameData;

extern GameData game_data;

void initialize_game_data();

#endif //SPACECOMPANY_GAME_DATA_H
