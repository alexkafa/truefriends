package com.example.truefriends;

public class Round {

    private final int number;
    private final Team team;
    private final Question question;
    private boolean answer;

    public Round(int number, Team team, Question question){
        this.number = number;
        this.team = team;
        this.question = question;
    }


    // Getters
    public int getNumber() {return number;}
    public Team getTeam() {return team;}
    public Question getQuestion() {return question;}
    public boolean getAnswer() {return answer;}


    // Setters
    public void setAnswer(boolean answer) {this.answer = answer;}
}
