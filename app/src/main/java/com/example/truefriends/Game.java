package com.example.truefriends;

import android.content.Context;
import android.util.Log;

import com.example.truefriends.data.QuestionRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game {

    private final Team team1;
    private final Team team2;
    private Round currentRound;
    private final Random random;
    private final QuestionRepository questionRepository;

    // Track used question IDs
    private final Set<Integer> usedQuestionIdsTeam1 = new HashSet<>();
    private final Set<Integer> usedQuestionIdsTeam2 = new HashSet<>();

    public Game(Team team1, Team team2, Context context){
        this.team1 = team1;
        this.team2 = team2;
        this.random = new Random();
        this.currentRound = new Round(1, team1);
        this.questionRepository = new QuestionRepository(context);
    }

    public void newRound(){
        if (currentRound.getTeam().equals(team1)){
            currentRound = new Round(currentRound.getNumber() + 1, team2);
        } else {
            currentRound = new Round(currentRound.getNumber() + 1, team1);
        }
        Log.d("Game", "New round started: " + currentRound.getNumber());
    }

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

    public void giveAnswer(boolean correct){
        currentRound.setAnswer(correct);
        Log.d("Game", "Answer given for round " + currentRound.getNumber() + ": " + (correct ? "correct" : "incorrect"));
    }

    public String getWinningTeam(){
        if (team1.getPoints() > team2.getPoints()){
            return "Team " + team1.getName() + " won!";
        } else if (team2.getPoints() > team1.getPoints()){
            return "Team " + team2.getName() + " won!";
        } else {
            return "It's a draw!";
        }
    }

    public Round getCurrentRound() { return currentRound; }
    public Team getTeam1() { return team1; }
    public Team getTeam2() { return team2; }
}
