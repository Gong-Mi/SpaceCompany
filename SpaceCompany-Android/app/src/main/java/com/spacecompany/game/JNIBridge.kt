package com.spacecompany.game

object JNIBridge {
    external fun tick(gameStateJson: String, delta: Double): String
    external fun buyBuilding(gameStateJson: String, buildingId: String): String
    external fun buyTech(gameStateJson: String, techId: String): String

    init {
        System.loadLibrary("spacecompany-native")
    }
}
