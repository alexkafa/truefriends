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

        switch (view.getId()) {
            case 1:
                category = "Dates";
                break;
            case 2:
                category = "Hopes and Dreams";
                break;
            case 3:
                category = "Gossip";
                break;
            case 4:
                category = "Binary";
                break;
            case 5:
                category = "Top 5";
                break;
            case 6:
                category = "Names";
                break;
            case 7:
                category = "Ancient History";
                break;
            case 8:
                category = "Hobbies";
                break;
            case 9:
                category = "True Friends";
                break;
        }
        System.out.println(category);

        intent.putExtra("QUESTION", category);
       // startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
    }
}
