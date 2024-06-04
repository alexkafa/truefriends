package com.example.truefriends;

import android.content.Context;

import com.example.truefriends.data.QuestionRepository;

import java.util.List;
import java.util.Random;

public class Game {

    private final Team team1;
    private final Team team2;
    private Round currentRound;
    private final Random random;
    private final QuestionRepository questionRepository;

    public Game(Team team1, Team team2, Context context){
        this.team1 = team1;
        this.team2 = team2;
        this.random = new Random();
        this.currentRound = new Round(1, team1);
        this.questionRepository = new QuestionRepository(context);
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
        List<Question> questionList = questionRepository.getAllQuestionsOfCategory(category);
        //List<Question> questionList = questionRepository.getAllQuestions();
        Question newQuestion =  questionList.stream()
                .filter(q -> q.getCategory().equals(category))
                .skip(random.nextInt((int) questionList.stream().filter(q -> q.getCategory().equals(category)).count()))
                .findFirst()
                .orElse(null);

        currentRound.setQuestion(newQuestion);
        //assert newQuestion != null;
        //System.out.println(newQuestion.getQuestion());
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
