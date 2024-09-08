package com.example.truefriends;

import android.content.Context;
import android.util.Log;

import com.example.truefriends.data.QuestionRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The Game class manages the overall flow of a game session between two teams.
 * It handles rounds, questions, and keeps track of which questions have already been used.
 */
public class Game {

    private final Team team1; // The first team participating in the game
    private final Team team2; // The second team participating in the game
    private Round currentRound; // The current round in the game
    private final Random random; // Random instance used for selecting questions
    private final QuestionRepository questionRepository; // Repository for retrieving questions from the database

    // Track used question IDs for each team to prevent repeating questions
    private final Set<Integer> usedQuestionIdsTeam1 = new HashSet<>();
    private final Set<Integer> usedQuestionIdsTeam2 = new HashSet<>();

    /**
     * Constructs a new Game instance with two teams and a context.
     *
     * @param team1   The first team
     * @param team2   The second team
     * @param context The context used to initialize the QuestionRepository
     */
    public Game(Team team1, Team team2, Context context) {
        this.team1 = team1;
        this.team2 = team2;
        this.random = new Random();
        this.currentRound = new Round(1, team1); // Start the game with round 1 and team 1
        this.questionRepository = new QuestionRepository(context);
    }

    /**
     * Starts a new round in the game.
     * Alternates the team for each round and increments the round number.
     */
    public void newRound() {
        if (currentRound.getTeam().equals(team1)) {
            currentRound = new Round(currentRound.getNumber() + 1, team2); // Switch to team 2
        } else {
            currentRound = new Round(currentRound.getNumber() + 1, team1); // Switch to team 1
        }
        Log.d("Game", "New round started: " + currentRound.getNumber());
    }

    /**
     * Retrieves a new question from the specified category.
     * Ensures that the question hasn't been used by the current team before.
     *
     * @param category The category from which to retrieve a question
     * @return A new Question object, or null if no questions are available
     */
    public Question newQuestion(Category category) {
        List<Question> questionList = questionRepository.getAllQuestionsOfCategory(category);

        if (questionList.isEmpty()) {
            Log.e("Game", "No questions available for category: " + category);
            return null;
        }

        // Determine which set of used question IDs to use
        Set<Integer> usedQuestionIds = currentRound.getTeam().equals(team1) ? usedQuestionIdsTeam1 : usedQuestionIdsTeam2;

        // Filter out used questions based on their IDs
        questionList.removeIf(question -> usedQuestionIds.contains(question.getId()));

        if (questionList.isEmpty()) {
            Log.e("Game", "No more unused questions available for category: " + category);
            return null; // or throw an exception, or return a default question
        }

        // Select a random question from the remaining list
        Question newQuestion = questionList.get(random.nextInt(questionList.size()));
        currentRound.setQuestion(newQuestion);

        // Mark the question ID as used for the current team
        usedQuestionIds.add(newQuestion.getId());

        Log.d("Game", "New question set for round " + currentRound.getNumber() + ": " + newQuestion.getQuestion());
        return newQuestion;
    }

    /**
     * Records the answer for the current round.
     *
     * @param correct True if the answer is correct, false otherwise
     */
    public void giveAnswer(boolean correct) {
        currentRound.setAnswer(correct);
        Log.d("Game", "Answer given for round " + currentRound.getNumber() + ": " + (correct ? "correct" : "incorrect"));
    }

    /**
     * Determines the winning team based on the total points accumulated.
     *
     * @return A string indicating which team won, or if the game is a draw
     */
    public String getWinningTeam() {
        if (team1.getPoints() > team2.getPoints()) {
            return "Team " + team1.getName() + " won!";
        } else if (team2.getPoints() > team1.getPoints()) {
            return "Team " + team2.getName() + " won!";
        } else {
            return "It's a draw!";
        }
    }

    // Getters for current round and teams
    public Round getCurrentRound() { return currentRound; }
    public Team getTeam1() { return team1; }
    public Team getTeam2() { return team2; }
}
