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