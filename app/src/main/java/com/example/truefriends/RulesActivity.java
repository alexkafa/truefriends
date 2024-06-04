package com.example.truefriends;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        String rules = "Have you seen this episode on Friends, where the girls argue with the " +
                "boys about who knows whom the best and then they end up making a quiz game to actually " +
                "find out? Well, with this game it's your turn to find out how well you and your friends " +
                "know each other!\nThe rules are simple; Divide your friend group in two teams and pick " +
                "names for your teams. After that, you go through 20 rounds of questions that can be either " +
                "answered right or wrong - your opponent team decides that. Before each question, you get " +
                "to pick the category (see categories below). In the end the team with the highest score wins" +
                " and gets the good apartment...erh sorry...you can choose your own bet!\n\n\n" +
                "Categories:\n1. Dates: Questions that are answered with a date.\n2. Hopes and Dreams: " +
                "Questions about your friends' future plans\n3. Gossip: No explanation needed.\n4. Binary: Yes/No " +
                "questions.\n5. Top 5: Name the top 5...\n6. Names: Questions that are answered with names.\n" +
                "7. Ancient History: A.k.a. past events.\n8. Hobbies: A.k.a hobbies\n9. True Friends: " +
                "Your own 2 questions!";

        TextView textRules = findViewById(R.id.textView2);
        if (textRules != null) {
            textRules.setText(rules);
        } else {
            Log.e("RulesActivity", "TextView for rules not found");
        }
    }

    public void goBackToMain(View view) {
        Log.d("RulesActivity", "Navigating back to MainActivity");
        finish(); // Closes the current activity and returns to the previous one
    }
}
