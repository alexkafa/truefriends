package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private GameAPI gameAPI;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Retrieve the GameAPI instance from the global application context (MyApp)
        // This ensures that the game state is shared across all activities
        gameAPI = ((MyApp) getApplicationContext()).getGameAPI();

        // Check if GameAPI is successfully initialized
        if (gameAPI == null) {
            Log.e("ThirdActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if GameAPI is not initialized to avoid further errors
            return;
        }

        // Update the UI with the current team's name and the current round number
        TextView teamNameTextView = findViewById(R.id.textView);
        teamNameTextView.setText("Team: " + gameAPI.getCurrentTeamName() + ", Round: " + gameAPI.getGame().getCurrentRound().getNumber());

        // Display the score for Team A
        TextView teamAScoreTextView = findViewById(R.id.teamAScoreTextView);
        Team teamA = gameAPI.getGame().getTeam1();
        teamAScoreTextView.setText(teamA.getName() + ": " + teamA.getPoints());

        // Display the score for Team B
        TextView teamBScoreTextView = findViewById(R.id.teamBScoreTextView);
        Team teamB = gameAPI.getGame().getTeam2();
        teamBScoreTextView.setText(teamB.getName() + ": " + teamB.getPoints());
    }

    public void showQuestion(View view) {
        // Ensure GameAPI is still initialized before proceeding
        if (gameAPI == null) {
            Log.e("ThirdActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, QuestionActivity.class);
        Category category;
        int id = view.getId(); // Get the ID of the clicked button to determine the category

        // Determine which category was selected based on the button ID
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
            category = Category.TRUE_FRIEND; // Default to TRUE_FRIEND if no matching ID is found
        }

        // Use the selected category to retrieve a question from the GameAPI
        String question = gameAPI.chooseCategoryButton(category);
        if (question.equals("No questions available for the selected category.")) {
            // Handle the case where no questions are available for the chosen category
            Log.e("ThirdActivity", "No questions available for category: " + category);
            Toast.makeText(this, "No questions available for the selected category.", Toast.LENGTH_SHORT).show();
            return; // Exit the method without proceeding to the next activity
        }

        // Pass the retrieved question to the QuestionActivity
        intent.putExtra("QUESTION", question);
        startActivity(intent); // Start the QuestionActivity to display the selected question
    }

    public void cancel(View view) {
        // Navigate back to the MainActivity when the cancel button is clicked
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
