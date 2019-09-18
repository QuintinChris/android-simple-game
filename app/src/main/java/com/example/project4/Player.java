package com.example.project4;

public class Player {

    private int playerId;
    private String name;
    private int wins;
    private int losses;
    private int ties;

    public Player() {
        playerId = 0;
        name = "";
        wins = 0;
        losses = 0;
        ties = 0;
    }
    public Player(int playerId, String name, int wins, int losses, int ties){
        this.playerId = playerId;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public int getPlayerId(){
        return playerId;
    }

    public void setPlayerId(){
        this.playerId = playerId;
    }

    public String getName(){
        return name;
    }

    public void setPlayerName(String name){
        this.name = name;
    }

    public int getWins(){
        return wins;
    }

    public void setWins(){
        this.wins = wins;
    }

    public int getLosses(){
        return losses;
    }

    public void setLosses(){
        this.losses = losses;
    }

    public int getTies(){
        return ties;
    }

    public void setTies(){
        this.ties = ties;
    }


}