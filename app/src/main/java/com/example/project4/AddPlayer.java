package com.example.project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddPlayer extends AppCompatActivity {

    PlayerDB db;
    EditText playerInput;
    Button addPlayerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        // Call DB
        db = new PlayerDB(this);

        // Setting UI
        playerInput = (EditText) findViewById(R.id.addPlayerInput);
        addPlayerButton = (Button) findViewById(R.id.addPlayerButton);

        // On button click, name entered will be inserted as new player in db
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = playerInput.getText().toString();
                if (input.length() > 0){
                    // Call addPlayer to create new player with input=playerName
                    db.addPlayer(input);
                    // Toast to confirm
                    Toast.makeText(getApplicationContext(),"Player Added!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
