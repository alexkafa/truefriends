package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Third extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView teamNameTextView = findViewById(R.id.textView);
        teamNameTextView.setText("Team: "+MainActivity.gameAPI.getCurrentTeamName());
    }

    public void showQuestion(View view) {
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

        String question = MainActivity.gameAPI.chooseCategoryButton(category);
        if (question.equals("No questions available for the selected category.")) {
            // Handle the case where no question is available
            Log.e("Third", "No questions available for category: " + category);
            return; // or show a message to the user
        }

        intent.putExtra("QUESTION", question);
        startActivity(intent);
    }


    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
