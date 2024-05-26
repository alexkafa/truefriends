package com.example.truefriends;

import java.util.List;
import java.util.Random;

public class Game {

    private Team team1;
    private Team team2;
    private List<Question> questionList;

    private Team currentTeam;
    private Random random;

    public Game(Team team1, Team team2, List<Question> questionList){
        this.team1 = team1;
        this.team2 = team2;
        this.questionList = questionList;
        this.currentTeam = team1;
        this.random = new Random();
    }



}
