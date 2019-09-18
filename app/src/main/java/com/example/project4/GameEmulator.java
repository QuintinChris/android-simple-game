package com.example.project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEmulator extends AppCompatActivity {

    // Instantiate database
    PlayerDB db;

    // Setting buttons and TVs for layout
    Button p1Wins;
    Button p2Wins;
    Button tieGame;
    TextView p1Label;
    TextView p2Label;
    TextView gameLabel;
    TextView tvP1Label;
    TextView tvP2Label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);

        db = new PlayerDB(this);

        // Setting buttons and TVs for UI
        p1Wins = (Button) findViewById(R.id.BtnP1Wins);
        p2Wins = (Button) findViewById(R.id.BtnP2Wins);
        tieGame = (Button) findViewById(R.id.BtnTie);
        p1Label = (TextView) findViewById(R.id.tvP1);
        p2Label = (TextView) findViewById(R.id.tvP2);
        gameLabel = (TextView) findViewById(R.id.emulatorLabel);
        tvP1Label = (TextView) findViewById(R.id.labelP1);
        tvP2Label = (TextView) findViewById(R.id.labelP2);

        // Pulls name stored in p1/p2 Selection to check if empty
        final String p1Check = ((MyApplication) this.getApplication()).getP1Selection();
        final String p2Check = ((MyApplication) this.getApplication()).getP2Selection();

        //if no names have been selected, start select p1/p2 activity
        if (TextUtils.isEmpty(p1Check)){
            Intent myIntent = new Intent(GameEmulator.this, SelectP1.class);
            GameEmulator.this.startActivity(myIntent);
        }
        else if (TextUtils.isEmpty(p2Check)){
            Intent myIntent = new Intent(GameEmulator.this, SelectP2.class);
            GameEmulator.this.startActivity(myIntent);
        }
        else // if players are selected, run gameEmulator
        {

            //Pulls names stored in p1/p2 selection
            final String p1ToDisplay = ((MyApplication) this.getApplication()).getP1Selection();
            final String p2ToDisplay = ((MyApplication) this.getApplication()).getP2Selection();
            // Displays names in TextView
            p1Label.setText(p1ToDisplay);
            p2Label.setText(p2ToDisplay);


            // Button listener
            p1Wins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call db methods to find _id of p1, then add 1 to p1 wins
                    // NAME as param to get id, ID as param to add win
                    db.addOneWin(db.getPlayerID(p1ToDisplay));
                    // Call db methods to find _id of p2, then add 1 to p2 losses
                    db.addOneLoss(db.getPlayerID(p2ToDisplay));
                    // start scoreboard activity
                    Intent myIntent = new Intent(GameEmulator.this, Scoreboard.class);
                    GameEmulator.this.startActivity(myIntent);
                }
            });

            p2Wins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.addOneWin(db.getPlayerID(p2ToDisplay));
                    db.addOneLoss(db.getPlayerID(p1ToDisplay));
                    Intent myIntent = new Intent(GameEmulator.this, Scoreboard.class);
                    GameEmulator.this.startActivity(myIntent);
                }
            });

            tieGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.addOneTie(db.getPlayerID(p1ToDisplay));
                    db.addOneTie(db.getPlayerID(p2ToDisplay));
                    Intent myIntent = new Intent(GameEmulator.this, Scoreboard.class);
                    GameEmulator.this.startActivity(myIntent);
                }
            });


        }

    }
}