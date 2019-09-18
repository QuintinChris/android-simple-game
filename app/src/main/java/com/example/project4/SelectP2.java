package com.example.project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectP2 extends AppCompatActivity {

    PlayerDB db;
    StringBuilder sb;
    String secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_p2);

        // Setting database
        db = new PlayerDB(this);
        sb = new StringBuilder();

        // Setting UI
        TextView tvSelectP2 = (TextView) findViewById(R.id.tvSelectP2);
        TextView selectPlayer2Label = (TextView) findViewById(R.id.SelectP2Label);

        // Pulls names from db and populates ListView with names
        // String list gets player names from db
        List<String> playerNames = new ArrayList<String>(Arrays.asList(db.playerNames().split("\n")));
        final ListView listView = (ListView) findViewById(R.id.playerList);
        // adapter sets string list to list view
        ArrayAdapter<String> mNames = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playerNames);
        listView.setAdapter(mNames);

        // Get listview selection
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtains string of item that user chose from listview
                secondPlayer = listView.getItemAtPosition(position).toString();
                // Hide selected player from list
                if (listView.getChildAt(position).isEnabled()){
                    listView.getChildAt(position).setEnabled(false);
                }
                // Toast to confirm selection
                Toast.makeText(getApplicationContext(),"Player Selected!", Toast.LENGTH_LONG).show();
            }

        });

        // Sets firstPlayer selected from listView to p1Selection var to access in other activities
        ((MyApplication)this.getApplication()).setP2Selection(secondPlayer);


        // back button to go back to main activity?
    }
}
