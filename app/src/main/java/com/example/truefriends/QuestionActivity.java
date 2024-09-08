package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The QuestionActivity class represents an activity where users interact with a trivia question.
 * It displays the current question, handles user responses, and navigates to the appropriate screens based on the game state.
 */
public class QuestionActivity extends AppCompatActivity {
    private GameAPI gameAPI; // The GameAPI instance used to interact with game logic

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question); // Set the content view to the layout for this activity

        // Retrieve the GameAPI instance from the application context
        gameAPI = ((MyApp) getApplicationContext()).getGameAPI();
        if (gameAPI == null) {
            Log.e("QuestionActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if GameAPI is not initialized
            return;
        }

        // Initialize the UI components
        TextView teamNameTextView = findViewById(R.id.teamNameTextView);
        teamNameTextView.setText("Team: " + gameAPI.getCurrentTeamName() + ", Round: " + gameAPI.getGame().getCurrentRound().getNumber());

        TextView questionTextView = findViewById(R.id.textViewQuestion);
        String question = getIntent().getStringExtra("QUESTION"); // Get the question text from the intent
        if (question != null) {
            questionTextView.setText(question);

            TextView difficultyTextView = findViewById(R.id.difficultyTextView);
            difficultyTextView.setText("Difficulty: " + gameAPI.getGame().getCurrentRound().getQuestion().getDifficulty());
        } else {
            Log.e("QuestionActivity", "No question received.");
            Toast.makeText(this, "Error: No question received.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no question is received
        }
    }

    /**
     * Handles the case when the team answers incorrectly.
     *
     * @param view The view that was clicked
     */
    public void teamAnsweredWrong(View view) {
        processAnswer(false); // Process the answer with a 'false' indicating incorrect
    }

    /**
     * Handles the case when the team answers correctly.
     *
     * @param view The view that was clicked
     */
    public void teamAnsweredRight(View view) {
        processAnswer(true); // Process the answer with a 'true' indicating correct
    }

    /**
     * Processes the answer given by the team and navigates to the appropriate screen.
     *
     * @param answer Boolean indicating whether the answer was correct or incorrect
     */
    private void processAnswer(boolean answer) {
        if (gameAPI == null) {
            Log.e("QuestionActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the result of the answer
        String getWinner = gameAPI.answerButton(answer);
        if (getWinner == null) {
            // If no winner, proceed to the next question
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        } else {
            // If there's a winner, navigate to the result page
            Intent intent = new Intent(this, ResultPageActivity.class);
            intent.putExtra("RESULTS", getWinner);
            startActivity(intent);
        }
    }

    /**
     * Navigates back to the main activity.
     *
     * @param view The view that was clicked
     */
    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
