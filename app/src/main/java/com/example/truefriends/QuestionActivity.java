package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    private GameAPI gameAPI;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        gameAPI = ((MainActivity) getApplicationContext()).getGameAPI();
        if (gameAPI == null) {
            Log.e("QuestionActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if GameAPI is not initialized
            return;
        }

        TextView teamNameTextView = findViewById(R.id.teamNameTextView);
        teamNameTextView.setText("Team: " + gameAPI.getCurrentTeamName());

        TextView questionTextView = findViewById(R.id.textViewQuestion);
        String question = getIntent().getStringExtra("QUESTION");
        if (question != null) {
            questionTextView.setText(question);
        } else {
            Log.e("QuestionActivity", "No question received.");
            Toast.makeText(this, "Error: No question received.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no question is received
        }
    }

    public void teamAnsweredWrong(View view) {
        processAnswer(false);
    }

    public void teamAnsweredRight(View view) {
        processAnswer(true);
    }

    private void processAnswer(boolean answer) {
        if (gameAPI == null) {
            Log.e("QuestionActivity", "GameAPI is not initialized.");
            Toast.makeText(this, "Error: GameAPI is not initialized.", Toast.LENGTH_SHORT).show();
            return;
        }

        String getWinner = gameAPI.answerButton(answer);
        if (getWinner == null) {
            Intent intent = new Intent(this, Third.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ResultPage.class);
            intent.putExtra("RESULTS", getWinner);
            startActivity(intent);
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
