package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView questionTextView = findViewById(R.id.textViewQuestion);
        String question = getIntent().getStringExtra("QUESTION");
        if (question != null) {
            questionTextView.setText(question);
        }
    }

    public void teamAnsweredWrong(View view) {
        MainActivity.gameAPI.answerButton(false);
        Intent intent = new Intent(this, Third.class);
        startActivity(intent);
    }

    public void teamAnsweredRight(View view) {
        MainActivity.gameAPI.answerButton(true);
        Intent intent = new Intent(this, Third.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
