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
            currentRound = new Round(currentRound.getNumber()+1, team2);
        }
        else{
            currentRound = new Round(currentRound.getNumber()+1, team1);
        }
    }

    public Question newQuestion(Category category){
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

    public String getWinningTeam(){
        if (team1.getPoints()>team2.getPoints()){
            return "Team "+team1.getName()+" won!";
        }
        else if (team2.getPoints()>team1.getPoints()){
            return "Team "+team2.getName()+" won!";
        }
        else{
            return "It's a draw!";
        }
    }
    public Round getCurrentRound() {return currentRound;}
    public Team getTeam1() {return team1;}
    public Team getTeam2() {return team2;}

}
