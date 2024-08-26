package com.example.truefriends;

import android.content.Context;
import android.util.Log;

public class GameAPI {

    private final int totalRounds = 20;
    private Game game;
    private final Context context;

    public GameAPI(Context context){
        this.context = context;
    }

    public void startGame(String team1Name, String team2Name){
        this.game = new Game(new Team(team1Name), new Team(team2Name), context);
        Log.d("GameAPI", "Game started with teams: " + team1Name + " and " + team2Name);
    }

    public String chooseCategoryButton(Category category) {
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        Question question = game.newQuestion(category);
        if (question == null) {
            Log.e("GameAPI", "No questions available for the selected category: " + category);
            return "No questions available for the selected category.";
        }
        return question.getQuestion();
    }

    public String checkToContinue() {
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        Round currentRound = game.getCurrentRound();
        if (currentRound.getNumber() == totalRounds && currentRound.getTeam().getName().equals(game.getTeam2().getName())) {
            String winningTeam = game.getWinningTeam();
            Log.d("GameAPI", "Game ended. Winning team: " + winningTeam);
            return winningTeam;
        } else {
            game.newRound();
            return null;
        }
    }

    public String answerButton(boolean answer){
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        game.giveAnswer(answer);
        return checkToContinue();
    }

    public Game getGame(){
        return game;
    }

    public String getCurrentTeamName() {
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        return game.getCurrentRound().getTeam().getName();
    }
}
