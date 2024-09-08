package com.example.truefriends;

import android.app.Application;

/**
 * The MyApp class extends Application to provide a global context for the app.
 * It initializes the GameAPI instance that can be accessed throughout the application.
 */
public class MyApp extends Application {
    private GameAPI gameAPI; // The GameAPI instance for global access

    @Override
    public void onCreate() {
        super.onCreate();
        gameAPI = new GameAPI(this); // Initialize GameAPI with the application context
    }

    /**
     * Provides access to the GameAPI instance.
     *
     * @return The GameAPI instance
     */
    public GameAPI getGameAPI() {
        return gameAPI;
    }
}
