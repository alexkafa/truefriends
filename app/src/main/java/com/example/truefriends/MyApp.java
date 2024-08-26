package com.example.truefriends;

import android.app.Application;

public class MyApp extends Application {
    private GameAPI gameAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        gameAPI = new GameAPI(this); // Initialize GameAPI with the application context
    }

    public GameAPI getGameAPI() {
        return gameAPI;
    }
}
