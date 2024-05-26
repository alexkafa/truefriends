package com.example.truefriends;

import java.util.List;
import java.util.Random;

public class Game {

    private final Team team1;
    private final Team team2;
    private final List<Question> questionList;

    private Round currentRound;
    private final Random random;

    public Game(Team team1, Team team2, List<Question> questionList){
        this.team1 = team1;
        this.team2 = team2;
        this.questionList = questionList;
        this.random = new Random();
        this.currentRound = new Round(1, team1);
    }

    public void newRound(){
        if (currentRound.getTeam().equals(team1)){
            currentRound = new Round(currentRound.getNumber(), team2);
        }
        else{
            currentRound = new Round(currentRound.getNumber(), team1);
        }
    }

    public Question newQuestion(String category){
        Question newQuestion =  questionList.stream()
                .filter(q -> q.getCategory().equals(category))
                .skip(random.nextInt((int) questionList.stream().filter(q -> q.getCategory().equals(category)).count()))
                .findFirst()
                .orElse(null);

        currentRound.setQuestion(newQuestion);
        return newQuestion;
    }

    public void giveAnswer(boolean correct){
        currentRound.setAnswer(correct);
    }

    public Round getCurrentRound() {return currentRound;}

}
