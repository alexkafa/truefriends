package com.example.truefriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static GameAPI gameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
