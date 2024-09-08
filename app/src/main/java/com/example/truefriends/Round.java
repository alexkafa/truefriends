package com.example.truefriends;

public class Round {

    private final int number; // The round number
    private final Team team; // The team associated with this round
    private Question question; // The question for this round
    private boolean answer; // The answer given for this round

    /**
     * Constructor for initializing a Round object.
     *
     * @param number The round number
     * @param team The team participating in this round
     */
    public Round(int number, Team team){
        this.number = number;
        this.team = team;
    }

    // Getters
    public int getNumber() { return number; } // Returns the round number
    public Team getTeam() { return team; } // Returns the team associated with this round
    public Question getQuestion() { return question; } // Returns the question for this round
    public boolean getAnswer() { return answer; } // Returns the answer for this round

    // Setters
    /**
     * Sets the question for this round.
     *
     * @param question The question to be set
     */
    public void setQuestion(Question question) { this.question = question; }

    /**
     * Sets the answer for this round and updates the team's points if the answer is correct.
     *
     * @param answer The answer provided for the question
     * @throws IllegalStateException if the question is not set before setting the answer
     */
    public void setAnswer(boolean answer) {
        if (question == null) {
            throw new IllegalStateException("Question must be set before setting the answer.");
        }
        this.answer = answer;
        if (answer) {
            team.addPoints(question.getDifficulty().getPoints()); // Update the team's points based on the question's difficulty
        }
    }
}
