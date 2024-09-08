package com.example.truefriends;

import android.content.Context;
import android.util.Log;

/**
 * The GameAPI class provides an interface for interacting with the game.
 * It handles game setup, category selection, answer checking, and game continuation.
 */
public class GameAPI {

    private final int totalRounds = 20; // Total number of rounds in the game
    private Game game; // The current game instance
    private final Context context; // Context for initializing game components

    /**
     * Constructs a new GameAPI instance with the provided context.
     *
     * @param context The context used for initializing the game
     */
    public GameAPI(Context context) {
        this.context = context;
    }

    /**
     * Starts a new game with the specified team names.
     * Initializes the Game instance and logs the game start event.
     *
     * @param team1Name The name of the first team
     * @param team2Name The name of the second team
     */
    public void startGame(String team1Name, String team2Name) {
        this.game = new Game(new Team(team1Name), new Team(team2Name), context);
        Log.d("GameAPI", "Game started with teams: " + team1Name + " and " + team2Name);
    }

    /**
     * Retrieves a new question based on the selected category.
     * Logs and returns the question or an error message if no questions are available.
     *
     * @param category The category for which to retrieve a question
     * @return The question text or an error message
     */
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

    /**
     * Checks whether the game should continue or end based on the current round.
     * If the game is at the last round and it is team 2's turn, determines the winning team.
     *
     * @return The winning team or null if the game should continue
     */
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
            return null; // Continue to the next round
        }
    }

    /**
     * Records the answer for the current round and checks whether the game should continue.
     *
     * @param answer True if the answer is correct, false otherwise
     * @return The result of checking to continue or an error message if the game hasn't started
     */
    public String answerButton(boolean answer) {
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        game.giveAnswer(answer);
        return checkToContinue(); // Check if the game should continue or end
    }

    /**
     * Gets the current Game instance.
     *
     * @return The current Game instance
     */
    public Game getGame() {
        return game;
    }

    /**
     * Retrieves the name of the team whose turn it is in the current round.
     *
     * @return The name of the current team or an error message if the game hasn't started
     */
    public String getCurrentTeamName() {
        if (game == null) {
            Log.e("GameAPI", "Game has not been started yet.");
            return "Game has not been started yet.";
        }
        return game.getCurrentRound().getTeam().getName();
    }
}
