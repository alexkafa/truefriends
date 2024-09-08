package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private GameAPI gameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the GameAPI instance from the application context
        gameAPI = ((MyApp) getApplicationContext()).getGameAPI();

        // Set up the 'Next' button click listener
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve and trim the team names from the input fields
                EditText editTextTeam1 = findViewById(R.id.editTextTeam1);
                String team1Name = editTextTeam1.getText().toString().trim();

                EditText editTextTeam2 = findViewById(R.id.editTextTeam2);
                String team2Name = editTextTeam2.getText().toString().trim();

                // Check if both team names are provided
                if (team1Name.isEmpty() || team2Name.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Please enter both team names.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Start the game using GameAPI
                if (gameAPI != null) {
                    gameAPI.startGame(team1Name, team2Name);
                    Log.d("SecondActivity", "Game started with teams: " + team1Name + " and " + team2Name);
                } else {
                    Log.e("SecondActivity", "GameAPI is not initialized.");
                    Toast.makeText(SecondActivity.this, "Error initializing game.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Navigate to the next activity (ThirdActivity)
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        // Set up the 'Back' button click listener
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close this activity and return to the previous one
                Log.d("SecondActivity", "Returning to previous activity.");
            }
        });
    }
}
