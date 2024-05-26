package com.example.truefriends;

import java.util.List;

public class GameAPI {

    private List<Question> questionList;
    private final int totalRounds = 10;
    private Game game;

    public GameAPI(){

    }

    public void setQuestionList(List<Question> questionList){
        this.questionList = questionList;
    }

    public void startGame(String team1Name, String team2Name){
        this.game = new Game(new Team(team1Name), new Team(team2Name), questionList);
    }

    public String chooseCategoryButton(String category){
        return game.newQuestion(category).getQuestion();
    }

    public void checkToContinue(){
        if (game.getCurrentRound().getNumber()<totalRounds){
            game.newRound();
        }
        else{
            System.out.println(game.getWinningTeam());
        }
    }

    public void answerButton(boolean answer){
        game.giveAnswer(answer);
        checkToContinue();
    }

    public Game getGame(){
        return game;
    }

    public String getCurrentTeamName() {
        return game.getCurrentRound().getTeam().getName();
    }

}
