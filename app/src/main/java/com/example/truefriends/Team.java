package com.example.truefriends;

public class Team {

    private final String name;
    private int points;


    public Team(String name){
        this.name = name;
        points = 0;
    }

    // Getters
    public String getName() {return name;}
    public int getPoints() {return points;}


    // Setters
    public void setPoints(int points) {this.points = points;}


    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
    }
}