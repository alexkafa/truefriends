package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    private GameAPI gameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gameAPI = ((MyApp) getApplicationContext()).getGameAPI();

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextTeam1 = findViewById(R.id.editTextTeam1);
                String team1Name = editTextTeam1.getText().toString().trim();

                EditText editTextTeam2 = findViewById(R.id.editTextTeam2);
                String team2Name = editTextTeam2.getText().toString().trim();

                if (team1Name.isEmpty() || team2Name.isEmpty()) {
                    Toast.makeText(Second.this, "Please enter both team names.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (gameAPI != null) {
                    gameAPI.startGame(team1Name, team2Name);
                    Log.d("Second", "Game started with teams: " + team1Name + " and " + team2Name);
                } else {
                    Log.e("Second", "GameAPI is not initialized.");
                    Toast.makeText(Second.this, "Error initializing game.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Second.this, Third.class);
                startActivity(intent);
            }
        });

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Closes the current activity and returns to the previous one
                Log.d("Second", "Returning to previous activity.");
            }
        });
    }
}
