#ifndef SPACECOMPANY_GAME_STATE_H
#define SPACECOMPANY_GAME_STATE_H

// Mirroring the Resource enum from Kotlin
typedef enum {
    ENERGY, PLASMA, URANIUM, LAVA, OIL, METAL, GEM, CHARCOAL, WOOD, SILICON,
    LUNARITE, METHANE, TITANIUM, GOLD, SILVER, HYDROGEN, HELIUM, ICE, METEORITE, SCIENCE, ROCKETFUEL,
    RESOURCE_COUNT // To get the number of resources
} Resource;

// Mirroring the ResourceState data class
typedef struct {
    Resource id;
    double current;
    double perSecond;
    double capacity;
    int unlocked; // boolean
    // desc is part of static data, not dynamic state
} ResourceState;

// Mirroring the BuildingState data class
typedef struct {
    char* id;
    long count;
} BuildingState;

// Mirroring the TechState data class
typedef struct {
    char* id;
    int current;
    int unlocked; // boolean
} TechState;

// Mirroring the PlanetState data class
typedef struct {
    char* id;
    int isExplored; // boolean
} PlanetState;


// The main GameState struct
typedef struct {
    ResourceState resources[RESOURCE_COUNT];
    
    BuildingState buildings[100];
    int num_building_types;

    TechState techs[100];
    int num_tech_types;

    PlanetState planets[50];
    int num_planet_types;

    // Modifiers map (simplified)
    struct {
        char key[64];
        double value;
    } modifiers[50];
    int num_modifiers;

    int rocketIsBuilt;
    int globalEnergyLock;

    // We will omit fields not needed by the core C logic for now
} GameState;

#endif //SPACECOMPANY_GAME_STATE_H
