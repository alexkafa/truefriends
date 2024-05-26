package com.example.truefriends;

public class Team {

    private final String name;
    private int points;
    private boolean isMyTurn;
    private final boolean isTeamA;

    public Team(String name, boolean isTeamA){
        this.name = name;
        points = 0;
        this.isTeamA = isTeamA;
        this.isMyTurn = isTeamA;
    }

    // Getters
    public String getName() {return name;}
    public int getPoints() {return points;}
    public boolean getMyTurn() {return isMyTurn;}
    public boolean getIsTeamA() {return isTeamA;}

    // Setters
    public void setPoints(int points) {this.points = points;}
    public void setIsMyTurn(boolean isMyTurn) {this.isMyTurn = isMyTurn;}


    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
    }
}