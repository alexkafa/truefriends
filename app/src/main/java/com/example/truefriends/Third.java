package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class Third extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void showQuestion(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        String category = "Default category";
        int id = view.getId();

        if (id == R.id.imageButton1) {
            category = "Dates";
        }
        else if (id == R.id.imageButton2) {
            category = "Hopes and Dreams";
        }
        else if (id == R.id.imageButton3) {
            category = "Gossip";
        }
        else if (id == R.id.imageButton4) {
            category = "Binary";
        }
        else if (id == R.id.imageButton5) {
            category = "Top 5";
        }
        else if (id == R.id.imageButton6) {
            category = "Names";
        }
        else if (id == R.id.imageButton7) {
            category = "Ancient History";
        }
        else if (id == R.id.imageButton8) {
            category = "Hobbies";
        }
        else {
            category = "True Friends";
        }

        //System.out.println(category);

        intent.putExtra("QUESTION", category);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
    }
}
