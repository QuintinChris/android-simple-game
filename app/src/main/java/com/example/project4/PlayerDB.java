package com.example.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PlayerDB {

    // Database constants
    public static final String DB_NAME = "player.db";
    public static final int DB_VERSION = 1;

    // player table constants
    public static final String PLAYER_TABLE = "Player";

    public static final String PLAYER_ID = "_id";
    public static final int PLAYER_ID_COL = 0;

    public static final String PLAYER_NAME = "Name";
    public static final int PLAYER_NAME_COL = 1;

    public static final String PLAYER_WINS = "Wins";
    public static final int PLAYER_WINS_COL = 2;

    public static final String PLAYER_LOSSES = "Losses";
    public static final int PLAYER_LOSSES_COL = 3;

    public static final String PLAYER_TIES = "Ties";
    public static final int PLAYER_TIES_COL = 4;

    public static final String CREATE_PLAYER_TABLE =
            "CREATE TABLE " + PLAYER_TABLE + " (" +
             PLAYER_ID      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             PLAYER_NAME    + " TEXT NOT NULL, " +
             PLAYER_WINS    + " INTEGER, " +
             PLAYER_LOSSES  + " INTEGER, " +
             PLAYER_TIES    + " INTEGER);";

    public static final String DROP_PLAYER_TABLE =
            "DROP TABLE IF EXISTS " + PLAYER_TABLE;


    public static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        // onCreate will create table when db is instantiated, also will add 3 players
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_PLAYER_TABLE);

            // Insert default players
            db.execSQL("INSERT INTO Player VALUES (1, 'Jimmy', 0, 0, 0)");
            db.execSQL("INSERT INTO Player VALUES (2, 'Hanna', 0, 0, 0)");
            db.execSQL("INSERT INTO Player VALUES (3, 'Xavier', 0, 0, 0)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.d("Player Table", "Upgrading db from version "
                    + oldVersion + "to " + newVersion);

            db.execSQL(PlayerDB.DROP_PLAYER_TABLE);
            onCreate(db);
        }

    }

    // database object and db helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public PlayerDB(Context context){
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB(){
        if (db != null)
            db.close();
    }



    // pulls all info from row for respective player
    private static Player getPlayerFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Player player = new Player(
                        cursor.getInt(PLAYER_ID_COL),
                        cursor.getString(PLAYER_NAME_COL),
                        cursor.getInt(PLAYER_WINS_COL),
                        cursor.getInt(PLAYER_LOSSES_COL),
                        cursor.getInt(PLAYER_TIES_COL));
                return player;
            }
            catch (Exception e){
                return null;
            }
        }
    }
    // this will return a row of corresponding player
    public Player getPlayer(int id) {
        String where = PLAYER_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(PLAYER_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Player player = getPlayerFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return player;
    }

    // method to retrieve all rows and columns from table
    public ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<Player>();
        openReadableDB();
        Cursor cursor = db.query(PLAYER_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            players.add(getPlayerFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return players;
    }



    // Method to retrieve only player names (for select player activity)
    public String playerNames(){
        openReadableDB();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT Name FROM Player;", null);
        if (cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex("Name"));
        }
        /*else if (cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex("Name"));
        }*/
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return result;
    }

    // Method to retrieve playerID based on parameter playerName
    public int getPlayerID(String name){
        openReadableDB();
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT " + PLAYER_ID + " FROM Player WHERE " + PLAYER_NAME + " = " + name + ";", null);
        if (cursor.moveToFirst()){
            result = cursor.getInt(cursor.getColumnIndex(PLAYER_ID));
        }

        return result;
    }



    // Method to add player
    // Requires name
    public long addPlayer(String playerName){
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_NAME, playerName);
        cv.put(PLAYER_WINS, 0);
        cv.put(PLAYER_LOSSES, 0);
        cv.put(PLAYER_TIES, 0);

        this.openWriteableDB();
        long newPlayerID = db.insert(PLAYER_TABLE, null, cv);
        this.closeDB();

        return newPlayerID;
    }

    // method to add 1 to wins, REQUIRES PLAYER_ID
    public void addOneWin(int playerId){
        this.openWriteableDB();
        db.execSQL("UPDATE Player SET PLAYER_WINS = PLAYER_WINS + 1 WHERE PLAYER_ID = " + playerId + ";");
        this.closeDB();
    }

    // method to add 1 to loss, REQUIRES PLAYER_ID
    public void addOneLoss(int playerId){
        this.openWriteableDB();
        db.execSQL("UPDATE Player SET PLAYER_LOSSES = PLAYER_LOSSES + 1 WHERE PLAYER_ID = " + playerId + ";");
        this.closeDB();
    }

    // method to add 1 to tie, REQUIRES PLAYER_ID
    public void addOneTie(int playerId){
        this.openWriteableDB();
        db.execSQL("UPDATE Player SET PLAYER_TIES = PLAYER_TIES + 1 WHERE PLAYER_ID = " + playerId + ";");
        this.closeDB();
    }

}