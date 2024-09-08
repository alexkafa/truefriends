package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The ResultPageActivity class represents an activity where the game results are displayed to the user.
 * It shows the results of the game and provides options to navigate back to the main activity or go to another activity.
 */
public class ResultPageActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge layout for immersive experience
        setContentView(R.layout.activity_result_page); // Set the content view to the layout for this activity

        // Adjust padding for window insets to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve and display the results
        TextView resultsTextView = findViewById(R.id.textViewResults);
        String results = getIntent().getStringExtra("RESULTS"); // Get results text from the intent
        if (results != null) {
            resultsTextView.setText(results);
        } else {
            Log.e("ResultPageActivity", "No results received.");
            resultsTextView.setText("No results available."); // Display a default message if no results are received
        }
    }

    /**
     * Navigates back to the MainActivity.
     *
     * @param view The view that was clicked
     */
    public void goBackToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.d("ResultPageActivity", "Navigated back to MainActivity."); // Log navigation event
    }

    /**
     * Navigates to the SecondActivity activity.
     *
     * @param view The view that was clicked
     */
    public void goToSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        Log.d("ResultPageActivity", "Navigated to SecondActivity activity."); // Log navigation event
    }
}
