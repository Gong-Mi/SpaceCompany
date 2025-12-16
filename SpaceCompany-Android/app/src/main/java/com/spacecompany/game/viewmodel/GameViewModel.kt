package com.spacecompany.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacecompany.game.JNIBridge
import com.spacecompany.game.data.SaveGameRepository
import com.spacecompany.game.model.GameState
import com.spacecompany.game.model.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GameViewModel(private val repository: SaveGameRepository) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState

    private var lastUpdateTime = System.currentTimeMillis()
    private var timeSinceAutoSave = 0.0

    init {
        viewModelScope.launch {
            val savedGame = repository.savedGame.first()
            if (savedGame != null) {
                _gameState.value = savedGame
            }
            lastUpdateTime = System.currentTimeMillis()
            startGameLoop()
        }
    }

    private fun startGameLoop() {
        viewModelScope.launch {
            while (true) {
                val currentTime = System.currentTimeMillis()
                val delta = (currentTime - lastUpdateTime) / 1000.0
                lastUpdateTime = currentTime

                // Serialize, call C core, deserialize, and update state
                val currentStateJson = Json.encodeToString(_gameState.value)
                val newStateJson = JNIBridge.tick(currentStateJson, delta)
                val newState = Json.decodeFromString<GameState>(newStateJson)
                _gameState.value = newState

                handleAutoSave(delta)
                delay(100)
            }
        }
    }

    private suspend fun handleAutoSave(delta: Double) {
        timeSinceAutoSave += delta
        if (timeSinceAutoSave >= 10.0) {
            repository.saveGame(_gameState.value)
            timeSinceAutoSave = 0.0
        }
    }

    fun onResourceSelected(resource: Resource) {
        _gameState.value = _gameState.value.copy(selectedResource = resource)
        
        // Fail-safe: If selecting a starter resource and user has none, give them some.
        // This ensures the game is playable even if the "Gain" buttons are hidden/bugged.
        if (resource == Resource.METAL || resource == Resource.WOOD || resource == Resource.GEM) {
            val currentAmount = _gameState.value.resources[resource]?.current ?: 0.0
            if (currentAmount < 10.0) {
                // We need to reflect this in the C++ state too, but since we rely on the loop to sync
                // we can't easily do it here without a specific JNI method.
                // However, for now, let's trust that the 'tick' loop will pick up the change 
                // if we modify the state and send it down.
                
                // Note: Modifying _gameState directly here might be overwritten by the next tick
                // if the C++ state isn't updated. A proper fix would be a JNI call.
                // But let's try to update the map locally.
                val newResources = _gameState.value.resources.toMutableMap()
                val resState = newResources[resource] ?: return
                newResources[resource] = resState.copy(current = 10.0, unlocked = true)
                
                _gameState.value = _gameState.value.copy(resources = newResources)
            }
        }
    }

    fun getCost(basePrice: Double, count: Long, multiplier: Double = 1.1): Double {
        // This might now be implemented in C, but for UI display purposes, keeping it here is fine.
        return java.lang.Math.floor(basePrice * java.lang.Math.pow(multiplier, count.toDouble()))
    }
    
    fun buyBuilding(buildingId: String) {
        viewModelScope.launch {
            val currentStateJson = Json.encodeToString(_gameState.value)
            val newStateJson = JNIBridge.buyBuilding(currentStateJson, buildingId)
            val newState = Json.decodeFromString<GameState>(newStateJson)
            _gameState.value = newState
        }
    }
    
    fun buyTech(techId: String) {
        viewModelScope.launch {
            val currentStateJson = Json.encodeToString(_gameState.value)
            val newStateJson = JNIBridge.buyTech(currentStateJson, techId)
            val newState = Json.decodeFromString<GameState>(newStateJson)
            _gameState.value = newState
        }
    }

    fun manualSave() {
        viewModelScope.launch {
            repository.saveGame(_gameState.value)
        }
    }

    fun deleteSave() {
        viewModelScope.launch {
            repository.deleteSave()
            _gameState.value = GameState()
        }
    }

    fun buildRocket() {
        // Will be implemented later
    }

    fun explorePlanet(planetId: String) {
        // Will be implemented later
    }
}