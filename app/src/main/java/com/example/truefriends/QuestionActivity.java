package com.example.truefriends;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView questionTextView = findViewById(R.id.questionTextView);
        String question = getIntent().getStringExtra("QUESTION");
        if (question != null) {
            questionTextView.setText(question);
        }
    }
}
