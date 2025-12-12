#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include "cJSON.h"
#include "game_state.h"
#include "game_data.h"
#include "tech_data.h"

#define LOG_TAG "NATIVE_LIB"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

// Forward declarations
void parse_game_state_from_json(const cJSON* root, GameState* gs);
char* serialize_game_state_to_json(const GameState* gs);
void core_tick(GameState* gs, double delta);
void core_buy_building(GameState* gs, const char* buildingId);
void core_buy_tech(GameState* gs, const char* techId);
double get_cost(double basePrice, long count);

static bool static_data_initialized = false;

void init_all_data() {
    if (!static_data_initialized) {
        initialize_game_data();
        initialize_tech_data();
        static_data_initialized = true;
    }
}

JNIEXPORT jstring JNICALL
Java_com_spacecompany_game_JNIBridge_tick(
        JNIEnv* env,
        jobject /* this */,
        jstring gameStateJson,
        jdouble delta) {

    init_all_data();

    const char *json_string = (*env)->GetStringUTFChars(env, gameStateJson, 0);
    cJSON *root = cJSON_Parse(json_string);

    GameState gs = {0};
    parse_game_state_from_json(root, &gs);

    core_tick(&gs, delta);

    char* new_json_string = serialize_game_state_to_json(&gs);

    cJSON_Delete(root);
    (*env)->ReleaseStringUTFChars(env, gameStateJson, json_string);

    jstring result = (*env)->NewStringUTF(env, new_json_string);
    free(new_json_string); 

    return result;
}

JNIEXPORT jstring JNICALL
Java_com_spacecompany_game_JNIBridge_buyBuilding(
        JNIEnv* env,
        jobject thiz,
        jstring gameStateJson,
        jstring buildingId) {

    init_all_data();

    const char *json_string = (*env)->GetStringUTFChars(env, gameStateJson, 0);
    const char *building_id_str = (*env)->GetStringUTFChars(env, buildingId, 0);
    cJSON *root = cJSON_Parse(json_string);

    GameState gs = {0};
    parse_game_state_from_json(root, &gs);

    core_buy_building(&gs, building_id_str);

    char* new_json_string = serialize_game_state_to_json(&gs);

    cJSON_Delete(root);
    (*env)->ReleaseStringUTFChars(env, gameStateJson, json_string);
    (*env)->ReleaseStringUTFChars(env, buildingId, building_id_str);

    jstring result = (*env)->NewStringUTF(env, new_json_string);
    free(new_json_string);

    return result;
}

JNIEXPORT jstring JNICALL
Java_com_spacecompany_game_JNIBridge_buyTech(
        JNIEnv* env,
        jobject thiz,
        jstring gameStateJson,
        jstring techId) {

    init_all_data();

    const char *json_string = (*env)->GetStringUTFChars(env, gameStateJson, 0);
    const char *tech_id_str = (*env)->GetStringUTFChars(env, techId, 0);
    cJSON *root = cJSON_Parse(json_string);

    GameState gs = {0};
    parse_game_state_from_json(root, &gs);

    core_buy_tech(&gs, tech_id_str);

    char* new_json_string = serialize_game_state_to_json(&gs);

    cJSON_Delete(root);
    (*env)->ReleaseStringUTFChars(env, gameStateJson, json_string);
    (*env)->ReleaseStringUTFChars(env, techId, tech_id_str);

    jstring result = (*env)->NewStringUTF(env, new_json_string);
    free(new_json_string);

    return result;
}

const char* resource_keys[RESOURCE_COUNT] = {
    "ENERGY", "PLASMA", "URANIUM", "LAVA", "OIL", "METAL", "GEM", "CHARCOAL", "WOOD", "SILICON",
    "LUNARITE", "METHANE", "TITANIUM", "GOLD", "SILVER", "HYDROGEN", "HELIUM", "ICE", "METEORITE", "SCIENCE", "ROCKETFUEL"
};

const BuildingData* find_building_data(const char* id) {
    for (int i = 0; i < game_data.num_buildings; i++) {
        if (strcmp(game_data.buildings[i].id, id) == 0) {
            return &game_data.buildings[i];
        }
    }
    return NULL;
}

const TechData* find_tech_data(const char* id) {
    for (int i = 0; i < tech_game_data.num_techs; i++) {
        if (strcmp(tech_game_data.techs[i].id, id) == 0) {
            return &tech_game_data.techs[i];
        }
    }
    return NULL;
}

double get_modifier(const GameState* gs, const char* key, double default_val) {
    for (int i = 0; i < gs->num_modifiers; i++) {
        if (strcmp(gs->modifiers[i].key, key) == 0) {
            return gs->modifiers[i].value;
        }
    }
    return default_val;
}

void parse_game_state_from_json(const cJSON* root, GameState* gs) {
    if (root == NULL) return;

    cJSON* resources_json = cJSON_GetObjectItem(root, "resources");
    for (int i = 0; i < RESOURCE_COUNT; i++) {
        cJSON* resource_item_json = cJSON_GetObjectItem(resources_json, resource_keys[i]);
        if (resource_item_json) {
            gs->resources[i].id = (Resource)i;
            gs->resources[i].current = cJSON_GetObjectItem(resource_item_json, "current")->valuedouble;
            gs->resources[i].capacity = cJSON_GetObjectItem(resource_item_json, "capacity")->valuedouble;
            gs->resources[i].unlocked = cJSON_GetObjectItem(resource_item_json, "unlocked")->valueint;
        }
    }

    cJSON* buildings_json = cJSON_GetObjectItem(root, "buildings");
    int building_count = 0;
    cJSON* building_item_json = NULL;
    cJSON_ArrayForEach(building_item_json, buildings_json) {
        if (building_count < 100) {
            gs->buildings[building_count].id = strdup(building_item_json->string);
            gs->buildings[building_count].count = (long)cJSON_GetObjectItem(building_item_json, "count")->valuedouble;
            building_count++;
        }
    }
    gs->num_building_types = building_count;

    cJSON* techs_json = cJSON_GetObjectItem(root, "techs");
    int tech_count = 0;
    cJSON* tech_item_json = NULL;
    cJSON_ArrayForEach(tech_item_json, techs_json) {
        if (tech_count < 100) {
            gs->techs[tech_count].id = strdup(tech_item_json->string);
            gs->techs[tech_count].current = cJSON_GetObjectItem(tech_item_json, "current")->valueint;
            gs->techs[tech_count].unlocked = cJSON_GetObjectItem(tech_item_json, "unlocked")->valueint;
            tech_count++;
        }
    }
    gs->num_tech_types = tech_count;
    
    cJSON* modifiers_json = cJSON_GetObjectItem(root, "modifiers");
    int mod_count = 0;
    cJSON* mod_item_json = NULL;
    cJSON_ArrayForEach(mod_item_json, modifiers_json) {
        if (mod_count < 50) {
            strncpy(gs->modifiers[mod_count].key, mod_item_json->string, 63);
            gs->modifiers[mod_count].value = mod_item_json->valuedouble;
            mod_count++;
        }
    }
    gs->num_modifiers = mod_count;

    gs->rocketIsBuilt = cJSON_GetObjectItem(root, "rocketIsBuilt")->valueint;
    gs->globalEnergyLock = cJSON_GetObjectItem(root, "globalEnergyLock")->valueint;
}

char* serialize_game_state_to_json(const GameState* gs) {
    cJSON *root = cJSON_CreateObject();

    cJSON* resources_json = cJSON_CreateObject();
    for (int i = 0; i < RESOURCE_COUNT; i++) {
        cJSON* resource_item_json = cJSON_CreateObject();
        cJSON_AddNumberToObject(resource_item_json, "current", gs->resources[i].current);
        cJSON_AddNumberToObject(resource_item_json, "perSecond", gs->resources[i].perSecond);
        cJSON_AddNumberToObject(resource_item_json, "capacity", gs->resources[i].capacity);
        cJSON_AddBoolToObject(resource_item_json, "unlocked", gs->resources[i].unlocked);
        cJSON_AddItemToObject(resources_json, resource_keys[i], resource_item_json);
    }
    cJSON_AddItemToObject(root, "resources", resources_json);

    cJSON* buildings_json = cJSON_CreateObject();
    for (int i = 0; i < gs->num_building_types; i++) {
        cJSON* building_item_json = cJSON_CreateObject();
        cJSON_AddNumberToObject(building_item_json, "count", gs->buildings[i].count);
        cJSON_AddItemToObject(buildings_json, gs->buildings[i].id, building_item_json);
    }
    cJSON_AddItemToObject(root, "buildings", buildings_json);

    cJSON* techs_json = cJSON_CreateObject();
    for (int i = 0; i < gs->num_tech_types; i++) {
        cJSON* tech_item_json = cJSON_CreateObject();
        cJSON_AddNumberToObject(tech_item_json, "current", gs->techs[i].current);
        cJSON_AddBoolToObject(tech_item_json, "unlocked", gs->techs[i].unlocked);
        cJSON_AddItemToObject(techs_json, gs->techs[i].id, tech_item_json);
    }
    cJSON_AddItemToObject(root, "techs", techs_json);
    
    cJSON* modifiers_json = cJSON_CreateObject();
    for (int i = 0; i < gs->num_modifiers; i++) {
        cJSON_AddNumberToObject(modifiers_json, gs->modifiers[i].key, gs->modifiers[i].value);
    }
    cJSON_AddItemToObject(root, "modifiers", modifiers_json);

    cJSON_AddBoolToObject(root, "rocketIsBuilt", gs->rocketIsBuilt);
    cJSON_AddBoolToObject(root, "globalEnergyLock", gs->globalEnergyLock);
    
    char *json_string = cJSON_PrintUnformatted(root);
    cJSON_Delete(root);
    return json_string;
}

double get_cost(double basePrice, long count) {
    return floor(basePrice * pow(1.1, count));
}

void core_tick(GameState* gs, double delta) {
    double perSecondRates[RESOURCE_COUNT] = {0};

    // 1. Calculate base production rates
    for (int i = 0; i < gs->num_building_types; i++) {
        BuildingState* buildingState = &gs->buildings[i];
        if (buildingState->count > 0) {
            const BuildingData* buildingData = find_building_data(buildingState->id);
            if (buildingData != NULL) {
                for (int j = 0; j < buildingData->num_production; j++) {
                    Production prod = buildingData->production[j];
                    double rate = prod.rate;
                    
                    // Check for modifiers (e.g. charcoalEngineOutput)
                    // Construct modifier key: {buildingId}Output or {buildingId}{Resource}Input?
                    // Original JS used `charcoalEngineOutput`. We need to match that logic.
                    // For now, hardcode check for Charcoal Engine Energy output
                    if (strcmp(buildingState->id, "charcoalEngine") == 0 && prod.resource == ENERGY) {
                        rate = get_modifier(gs, "charcoalEngineOutput", rate);
                    }

                    perSecondRates[prod.resource] += rate * buildingState->count;
                }
            }
        }
    }

    // 2. Check for energy deficit
    double energyBalance = perSecondRates[ENERGY];
    bool energyLow = energyBalance < 0 && gs->resources[ENERGY].current <= 0;

    // 3. Recalculate production if energy is low
    if (energyLow) {
        for(int i = 0; i < RESOURCE_COUNT; i++) {
            if (i != ENERGY) {
                perSecondRates[i] = 0;
            }
        }

        for (int i = 0; i < gs->num_building_types; i++) {
            BuildingState* buildingState = &gs->buildings[i];
            if (buildingState->count > 0) {
                const BuildingData* buildingData = find_building_data(buildingState->id);
                if (buildingData != NULL) {
                    bool consumesEnergy = false;
                    for (int j = 0; j < buildingData->num_production; j++) {
                        if (buildingData->production[j].resource == ENERGY && buildingData->production[j].rate < 0) {
                            consumesEnergy = true;
                            break;
                        }
                    }
                    
                    if (!consumesEnergy) {
                        for (int j = 0; j < buildingData->num_production; j++) {
                            Production prod = buildingData->production[j];
                             if (prod.resource != ENERGY) {
                                perSecondRates[prod.resource] += prod.rate * buildingState->count;
                            }
                        }
                    }
                }
            }
        }
    }

    // 4. Update resource amounts based on final rates
    for (int i = 0; i < RESOURCE_COUNT; i++) {
        gs->resources[i].perSecond = perSecondRates[i];
        gs->resources[i].current += perSecondRates[i] * delta;
        if (gs->resources[i].capacity >= 0 && gs->resources[i].current > gs->resources[i].capacity) {
            gs->resources[i].current = gs->resources[i].capacity;
        }
        if (gs->resources[i].current < 0) {
            gs->resources[i].current = 0;
        }
    }
    
    gs->globalEnergyLock = energyLow;
}

void core_buy_building(GameState* gs, const char* buildingId) {
    const BuildingData* buildingData = find_building_data(buildingId);
    if (buildingData == NULL) return;

    BuildingState* buildingState = NULL;
    for (int i = 0; i < gs->num_building_types; i++) {
        if (strcmp(gs->buildings[i].id, buildingId) == 0) {
            buildingState = &gs->buildings[i];
            break;
        }
    }
    if (buildingState == NULL) return;

    bool can_afford = true;
    for (int i = 0; i < buildingData->num_cost; i++) {
        Cost cost = buildingData->cost[i];
        double required_amount = get_cost(cost.amount, buildingState->count);
        if (gs->resources[cost.resource].current < required_amount) {
            can_afford = false;
            break;
        }
    }

    if (can_afford) {
        for (int i = 0; i < buildingData->num_cost; i++) {
            Cost cost = buildingData->cost[i];
            double required_amount = get_cost(cost.amount, buildingState->count);
            gs->resources[cost.resource].current -= required_amount;
        }
        buildingState->count++;
    }
}

void core_buy_tech(GameState* gs, const char* techId) {
    const TechData* techData = find_tech_data(techId);
    if (techData == NULL) return;

    TechState* techState = NULL;
    for (int i = 0; i < gs->num_tech_types; i++) {
        if (strcmp(gs->techs[i].id, techId) == 0) {
            techState = &gs->techs[i];
            break;
        }
    }
    if (techState == NULL) return;

    if (techData->maxLevel != -1 && techState->current >= techData->maxLevel) return;

    bool can_afford = true;
    for (int i = 0; i < techData->num_cost; i++) {
        Cost cost = techData->cost[i];
        // Tech costs scale with level too
        double required_amount = get_cost(cost.amount, techState->current); 
        if (gs->resources[cost.resource].current < required_amount) {
            can_afford = false;
            break;
        }
    }

    if (can_afford) {
        for (int i = 0; i < techData->num_cost; i++) {
            Cost cost = techData->cost[i];
            double required_amount = get_cost(cost.amount, techState->current);
            gs->resources[cost.resource].current -= required_amount;
        }
        
        techState->current++;
        techState->unlocked = 1; // Ensure it's marked unlocked

        // Apply effects
        // 1. Modifiers
        if (techData->modifier_key != NULL) {
            // Update or add modifier
            bool found = false;
            for(int i=0; i<gs->num_modifiers; i++) {
                if(strcmp(gs->modifiers[i].key, techData->modifier_key) == 0) {
                    gs->modifiers[i].value = techData->modifier_value;
                    found = true;
                    break;
                }
            }
            if (!found && gs->num_modifiers < 50) {
                strncpy(gs->modifiers[gs->num_modifiers].key, techData->modifier_key, 63);
                gs->modifiers[gs->num_modifiers].value = techData->modifier_value;
                gs->num_modifiers++;
            }
        }

        // 2. Unlock new techs
        for (int i = 0; i < techData->num_unlocks_techs; i++) {
            const char* unlockId = techData->unlocks_techs[i];
            for (int j = 0; j < gs->num_tech_types; j++) {
                if (strcmp(gs->techs[j].id, unlockId) == 0) {
                    gs->techs[j].unlocked = 1;
                    break;
                }
            }
        }

        // 3. Unlock resources
        for (int i = 0; i < techData->num_unlocks_resources; i++) {
            Resource resId = techData->unlocks_resources[i];
            if (resId < RESOURCE_COUNT) {
                gs->resources[resId].unlocked = 1;
            }
        }
    }
}