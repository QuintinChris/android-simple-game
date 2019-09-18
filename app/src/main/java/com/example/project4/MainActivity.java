package com.example.project4;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Buttons to select activity
    Button startGame;
    Button viewScoreboard;
    Button addPlayer;
    Button selectP1;
    Button selectP2;

    PlayerDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        db = new PlayerDB(this);

        // Setting buttons to UI
        startGame = findViewById(R.id.BtnStart);
        viewScoreboard = findViewById(R.id.BtnScore);
        addPlayer = findViewById(R.id.BtnAddPlayer);
        selectP1 = findViewById(R.id.BtnP1);
        selectP2 = findViewById(R.id.BtnP2);

        // Button listener will open GameEmulator activity
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts GameEmulator activity when button is clicked
                //Intent i = new Intent(getApplicationContext(), GameEmulator.class);
                //startActivity(i);
                Intent myIntent = new Intent(MainActivity.this, GameEmulator.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        // Starts scoreboard activity to display scores
        viewScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts Scoreboard activity when button is clicked
                Intent myIntent = new Intent(MainActivity.this, Scoreboard.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


        // User adds player name, which gets added to playerList that will populate in ListView in SelectP1 and SelectP2
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts AddPlayer activity when button is clicked
                Intent myIntent = new Intent(MainActivity.this, AddPlayer.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


        // Will activate selectP1 activity to show list of players and confirm user selection
        selectP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts SelectP1 activity when button is clicked
                Intent myIntent = new Intent(MainActivity.this, SelectP1.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


        // Will activate selectP2 activity to show list of players and confirm user selection
        selectP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SelectP2.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


    }


}