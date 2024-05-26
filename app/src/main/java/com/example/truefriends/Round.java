package com.example.truefriends;

public class Round {

    private final int number;
    private final Team team;
    private Question question;
    private boolean answer;

    public Round(int number, Team team){
        this.number = number;
        this.team = team;
    }


    // Getters
    public int getNumber() {return number;}
    public Team getTeam() {return team;}
    public Question getQuestion() {return question;}
    public boolean getAnswer() {return answer;}


    // Setters
    public void setQuestion(Question question) {this.question = question;}
    public void setAnswer(boolean answer) {
        this.answer = answer;
        if (answer){
            team.addPoints(question.getValue());
        }
    }
}
