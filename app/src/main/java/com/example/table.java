package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.LeagueAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class mainactivity extends AppCompatActivity {

    private Spinner teamSpinner;
    private RecyclerView leagueTableRecyclerView;
    private TextView leagueTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamSpinner = findViewById(R.id.teamSpinner);
        leagueTableRecyclerView = findViewById(R.id.leagueTableRecyclerView);
        leagueTitle = findViewById(R.id.leagueTitle);


        HashMap<String, String> teamToLeagueMap = new HashMap<>();
        teamToLeagueMap.put("Barcelona", "La Liga");
        teamToLeagueMap.put("Real Madrid", "La Liga");
        teamToLeagueMap.put("Manchester United", "Premier League");
        teamToLeagueMap.put("Manchester City", "Premier League");
        teamToLeagueMap.put("Bayern Munich", "Bundesliga");
        teamToLeagueMap.put("Paris Saint-Germain", "Ligue 1");
        teamToLeagueMap.put("Juventus", "Serie A");
        teamToLeagueMap.put("Inter Milan", "Serie A");
        teamToLeagueMap.put("Atletico Madrid", "La Liga");
        teamToLeagueMap.put("Liverpool", "Premier League");

        // Sample league data with 20 teams for each league
        HashMap<String, List<String>> leagueTables = new HashMap<>();
        leagueTables.put("La Liga", Arrays.asList(
                "1. Barcelona", "2. Real Madrid", "3. Atletico Madrid", "4. Sevilla", "5. Real Sociedad",
                "6. Villarreal", "7. Athletic Club", "8. Betis", "9. Valencia", "10. Osasuna",
                "11. Mallorca", "12. Espanyol", "13. Celta Vigo", "14. Getafe", "15. Cadiz",
                "16. Valladolid", "17. Almeria", "18. Granada", "19. Girona", "20. Elche"));

        leagueTables.put("Premier League", Arrays.asList(
                "1. Arsenal", "2. Manchester City", "3. Manchester United", "4. Liverpool", "5. Newcastle United",
                "6. Tottenham Hotspur", "7. Chelsea", "8. Brighton", "9. Aston Villa", "10. Fulham",
                "11. Crystal Palace", "12. Brentford", "13. Wolverhampton", "14. West Ham", "15. Nottingham Forest",
                "16. Bournemouth", "17. Everton", "18. Burnley", "19. Sheffield United", "20. Luton Town"));

        leagueTables.put("Serie A", Arrays.asList(
                "1. Napoli", "2. Juventus", "3. Inter Milan", "4. AC Milan", "5. Lazio",
                "6. Roma", "7. Atalanta", "8. Fiorentina", "9. Torino", "10. Udinese",
                "11. Bologna", "12. Monza", "13. Sassuolo", "14. Empoli", "15. Verona",
                "16. Lecce", "17. Spezia", "18. Salernitana", "19. Genoa", "20. Cagliari"));

        leagueTables.put("Bundesliga", Arrays.asList(
                "1. Bayern Munich", "2. Borussia Dortmund", "3. RB Leipzig", "4. Bayer Leverkusen", "5. Eintracht Frankfurt",
                "6. Freiburg", "7. Union Berlin", "8. Mainz", "9. Wolfsburg", "10. Hoffenheim",
                "11. Werder Bremen", "12. Augsburg", "13. Stuttgart", "14. Bochum", "15. Cologne",
                "16. Hertha Berlin", "17. Schalke", "18. Hamburg", "19. Hannover 96", "20. Nuremberg"));

        leagueTables.put("Ligue 1", Arrays.asList(
                "1. Paris Saint-Germain", "2. Marseille", "3. Lyon", "4. Monaco", "5. Lille",
                "6. Rennes", "7. Lens", "8. Nice", "9. Strasbourg", "10. Montpellier",
                "11. Reims", "12. Lorient", "13. Toulouse", "14. Nantes", "15. Brest",
                "16. Angers", "17. Ajaccio", "18. Clermont Foot", "19. Metz", "20. Auxerre"));

        // Populate the Spinner with team names
        List<String> teams = new ArrayList<>(teamToLeagueMap.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(adapter);

        // Set up RecyclerView
        leagueTableRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Handle team selection
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTeam = teams.get(position);
                String league = teamToLeagueMap.get(selectedTeam);

                // Update UI
                leagueTitle.setText(league + " Table");
                leagueTitle.setVisibility(View.VISIBLE);
                leagueTableRecyclerView.setVisibility(View.VISIBLE);

                // Load league data into RecyclerView
                List<String> leagueData = leagueTables.get(league);
                LeagueAdapter leagueAdapter = new LeagueAdapter(leagueData);
                leagueTableRecyclerView.setAdapter(leagueAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                leagueTitle.setVisibility(View.GONE);
                leagueTableRecyclerView.setVisibility(View.GONE);
            }
        });
    }
}
