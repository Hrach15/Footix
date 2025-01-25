package com.example;// Java Example
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

class mainActivity extends AppCompatActivity {
    private Spinner leagueSpinner, teamSpinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // League and team data
        String[] leagues = {"Premier League", "La Liga", "Serie A", "Bundesliga", "Ligue 1"};
        String[][] teams = {
                {"Arsenal", "Manchester City", "Liverpool", "Chelsea", "Tottenham"},
                {"Real Madrid", "Barcelona", "Atletico Madrid", "Sevilla", "Valencia"},
                {"Juventus", "AC Milan", "Inter Milan", "Napoli", "Roma"},
                {"Bayern Munich", "Borussia Dortmund", "RB Leipzig", "Leverkusen", "Wolfsburg"},
                {"PSG", "Marseille", "Lyon", "Monaco", "Lille"}
        };

        // Initialize spinners
        leagueSpinner = findViewById(R.id.league_spinner);
        teamSpinner = findViewById(R.id.team_spinner);

        // Set up league spinner
        ArrayAdapter<String> leagueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, leagues);
        leagueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leagueSpinner.setAdapter(leagueAdapter);

        // Set up listener for league spinner
        leagueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Populate team spinner based on selected league
                ArrayAdapter<String> teamAdapter = new ArrayAdapter<>(mainActivity.this,
                        android.R.layout.simple_spinner_item, teams[position]);
                teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                teamSpinner.setAdapter(teamAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Clear the team spinner when no league is selected
                teamSpinner.setAdapter(null);
            }
        });
    }
}
