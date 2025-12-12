package com.spacecompany.game.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.spacecompany.game.model.GameState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "game_save")

class SaveGameRepository(private val context: Context) {

    private val SAVE_GAME_KEY = stringPreferencesKey("save_game")

    private external fun decompressFromBase64(base64: String): String?
    private external fun compressToBase64(raw: String): String?

    val savedGame: Flow<GameState?> = context.dataStore.data
        .map { preferences ->
            val compressedSave = preferences[SAVE_GAME_KEY] ?: return@map null
            val jsonString = decompressFromBase64(compressedSave)
            if (jsonString.isNullOrEmpty()) return@map null
            Json.decodeFromString<GameState>(jsonString)
        }

    suspend fun saveGame(gameState: GameState) {
        val jsonString = Json.encodeToString(gameState)
        val compressedString = compressToBase64(jsonString)
        context.dataStore.edit { preferences ->
            preferences[SAVE_GAME_KEY] = compressedString ?: ""
        }
    }

    suspend fun deleteSave() {
        context.dataStore.edit {
            it.clear()
        }
    }

    companion object {
        init {
            System.loadLibrary("spacecompany-native")
        }
    }
}
