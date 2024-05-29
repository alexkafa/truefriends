package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView teamNameTextView = findViewById(R.id.teamNameTextView);
        teamNameTextView.setText("Team: "+MainActivity.gameAPI.getCurrentTeamName());

        TextView questionTextView = findViewById(R.id.textViewQuestion);
        String question = getIntent().getStringExtra("QUESTION");
        if (question != null) {
            questionTextView.setText(question);
        }
    }

    public void teamAnsweredWrong(View view) {
        String getWinner = MainActivity.gameAPI.answerButton(false);
        if (getWinner == null) {
            Intent intent = new Intent(this, Third.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, ResultPage.class);
            intent.putExtra("RESULTS", getWinner);
            startActivity(intent);
        }
    }

    public void teamAnsweredRight(View view) {
        String getWinner = MainActivity.gameAPI.answerButton(true);
        if (getWinner == null) {
            Intent intent = new Intent(this, Third.class);
            startActivity(intent);
        }
        else{
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
