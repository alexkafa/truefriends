package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Second.this, Third.class);
                startActivity(intent);

                EditText editTextTeam1 = findViewById(R.id.editTextTeam1);
                String team1Name = editTextTeam1.getText().toString();

                EditText editTextTeam2 = findViewById(R.id.editTextTeam2);
                String team2Name = editTextTeam2.getText().toString();

                MainActivity.gameAPI.startGame(team1Name,team2Name);
            }
        });
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Closes the current activity and returns to the previous one
            }
        });
    }
}
