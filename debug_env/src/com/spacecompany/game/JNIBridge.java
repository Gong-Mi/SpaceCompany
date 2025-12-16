package com.spacecompany.game;

public class JNIBridge {
    public native String tick(String gameStateJson, double delta);
}
