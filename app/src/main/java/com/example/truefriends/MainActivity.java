package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truefriends.data.DatabaseHelper;
import com.example.truefriends.data.QuestionRepository;

public class MainActivity extends AppCompatActivity {

    private GameAPI gameAPI;
    private QuestionRepository questionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        // Initialize the database with sample data
        try {
            DBInitializer dbInitializer = new DBInitializer(this);
            dbInitializer.initializeDatabase(this);
            Log.d("MainActivity", "Database initialized successfully.");
        } catch (Exception e) {
            Log.e("MainActivity", "Error initializing database", e);
        }
         */

        // Initialize the database helper (without initializing with sample data)
        try {
            // You only need to ensure DatabaseHelper is instantiated to access existing data
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
            questionRepository = new QuestionRepository(this);
            Log.d("MainActivity", "Database accessed successfully.");
        } catch (Exception e) {
            Log.e("MainActivity", "Error accessing database", e);
        }

        gameAPI = new GameAPI(this);
    }

    public void openTeamSetUpPage(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
        Log.d("MainActivity", "Navigated to Team Setup Page.");
    }

    public void openRulesPage(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
        Log.d("MainActivity", "Navigated to Rules Page.");
    }

    public GameAPI getGameAPI() {
        return gameAPI;
    }
}
