package com.example.truefriends;

import java.util.List;

public class GameAPI {

    private List<Question> questionList;
    private final int totalRounds = 10;
    private Game game;
    //public QuestionGenerator questionGenerator;

    public GameAPI(){
        //questionGenerator = new QuestionGenerator();
        questionList = QuestionGenerator.generateQuestions();
    }

    /*
    public void setQuestionList(List<Question> questionList){
        this.questionList = questionList;
    }

     */

    public void startGame(String team1Name, String team2Name){
        this.game = new Game(new Team(team1Name), new Team(team2Name), questionList);
    }

    public String chooseCategoryButton(Category category){
        return game.newQuestion(category).getQuestion();
    }

    public String checkToContinue() {
        Round currentRound = game.getCurrentRound();
        if (currentRound.getNumber() == totalRounds && currentRound.getTeam().getName().equals(game.getTeam2().getName())) {
            return (game.getWinningTeam());
        } else {
            game.newRound();
            return null;
        }
    }

    public String answerButton(boolean answer){
        game.giveAnswer(answer);
        return checkToContinue();
    }

    public Game getGame(){
        return game;
    }

    public String getCurrentTeamName() {
        return game.getCurrentRound().getTeam().getName();
    }

}
