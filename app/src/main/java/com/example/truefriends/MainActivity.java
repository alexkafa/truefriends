package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truefriends.data.DatabaseHelper;
import com.example.truefriends.data.QuestionRepository;

/**
 * The MainActivity class is the main entry point of the application.
 * It handles initialization, navigation to other activities, and manages game setup.
 */
public class MainActivity extends AppCompatActivity {

    private GameAPI gameAPI; // The GameAPI instance for managing game operations
    private QuestionRepository questionRepository; // Repository for accessing questions in the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the main activity layout

        // Initialize the database helper and question repository
        try {
            // Access the database to ensure it is available; no need to initialize with sample data
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
            questionRepository = new QuestionRepository(this); // Initialize the question repository
            Log.d("MainActivity", "Database accessed successfully.");
        } catch (Exception e) {
            Log.e("MainActivity", "Error accessing database", e); // Log any errors in database access
        }

        gameAPI = new GameAPI(this); // Initialize the GameAPI instance
    }

    /**
     * Starts the Team Setup activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked
     */
    public void openTeamSetUpPage(View view) {
        Intent intent = new Intent(this, SecondActivity.class); // Create an intent to start the Team Setup activity
        startActivity(intent); // Start the new activity
        Log.d("MainActivity", "Navigated to Team Setup Page."); // Log navigation event
    }

    /**
     * Starts the Rules activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked
     */
    public void openRulesPage(View view) {
        Intent intent = new Intent(this, RulesActivity.class); // Create an intent to start the Rules activity
        startActivity(intent); // Start the new activity
        Log.d("MainActivity", "Navigated to Rules Page."); // Log navigation event
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
