package com.example.project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Scoreboard extends AppCompatActivity {

    // Instantiate database
    PlayerDB db;
    StringBuilder sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // Call DB
        db = new PlayerDB(this);
        sb = new StringBuilder();

        // Setting UI
        TextView scoresTV = (TextView) findViewById(R.id.scoresTV);

        // Display all players and scores
        ArrayList<Player> players = db.getPlayers();
        for (Player p : players){
            sb.append(p.getName() + "|" + p.getWins() + "|" + p.getLosses() + "|" + p.getTies() + "\n");
        }

        // Display string on UI
        scoresTV.setText(sb.toString());

    }

}
