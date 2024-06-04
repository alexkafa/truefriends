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

public class ResultPage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView resultsTextView = findViewById(R.id.textViewResults);
        String results = getIntent().getStringExtra("RESULTS");
        if (results != null) {
            resultsTextView.setText(results);
        } else {
            Log.e("ResultPage", "No results received.");
            resultsTextView.setText("No results available.");
        }
    }

    public void goBackToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.d("ResultPage", "Navigated back to MainActivity.");
    }

    public void goToSecond(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
        Log.d("ResultPage", "Navigated to Second activity.");
    }
}
