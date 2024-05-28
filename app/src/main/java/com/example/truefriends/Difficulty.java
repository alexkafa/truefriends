package com.example.truefriends;

public enum Difficulty {
    EASY(2),
    MEDIUM(3),
    HARD(5);

    private int points;

    Difficulty(int points){
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
}
