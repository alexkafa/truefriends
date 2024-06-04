package com.example.truefriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truefriends.data.DBInitializer;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static GameAPI gameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database with sample data
        DBInitializer dbInitializer = new DBInitializer(this);
        dbInitializer.initializeDatabase(this);

        gameAPI = new GameAPI(this);
    }

    public void openTeamSetUpPage(View view) {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
    }

    public void openRulesPage(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

}
