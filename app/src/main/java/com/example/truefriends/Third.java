package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Third extends AppCompatActivity {
    private GameAPI gameAPI;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        gameAPI = ((MyApp) getApplicationContext()).getGameAPI();
        if (gameAPI == null) {
            Log.e("Third", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if GameAPI is not initialized
            return;
        }

        TextView teamNameTextView = findViewById(R.id.textView);
        teamNameTextView.setText("Team: " + gameAPI.getCurrentTeamName() + ", Round: " + gameAPI.getGame().getCurrentRound().getNumber());

        TextView teamAScoreTextView = findViewById(R.id.teamAScoreTextView);
        Team teamA = gameAPI.getGame().getTeam1();
        teamAScoreTextView.setText(teamA.getName() + ": " + teamA.getPoints());

        TextView teamBScoreTextView = findViewById(R.id.teamBScoreTextView);
        Team teamB = gameAPI.getGame().getTeam2();
        teamBScoreTextView.setText(teamB.getName() + ": " + teamB.getPoints());
    }

    public void showQuestion(View view) {
        if (gameAPI == null) {
            Log.e("Third", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, QuestionActivity.class);
        Category category;
        int id = view.getId();

        if (id == R.id.imageButton1) {
            category = Category.DATES;
        } else if (id == R.id.imageButton2) {
            category = Category.HOPES_DREAMS;
        } else if (id == R.id.imageButton3) {
            category = Category.GOSSIP;
        } else if (id == R.id.imageButton4) {
            category = Category.BINARY_QUESTIONS;
        } else if (id == R.id.imageButton5) {
            category = Category.TOP_5;
        } else if (id == R.id.imageButton6) {
            category = Category.NAMES;
        } else if (id == R.id.imageButton7) {
            category = Category.ANCIENT_HISTORY;
        } else if (id == R.id.imageButton8) {
            category = Category.HOBBIES;
        } else {
            category = Category.TRUE_FRIEND;
        }

        String question = gameAPI.chooseCategoryButton(category);
        if (question.equals("No questions available for the selected category.")) {
            Log.e("Third", "No questions available for category: " + category);
            Toast.makeText(this, "No questions available for the selected category.", Toast.LENGTH_SHORT).show();
            return; // Show a message to the user
        }

        intent.putExtra("QUESTION", question);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
