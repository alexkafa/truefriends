package com.example.truefriends;

/**
 * The Question class represents a trivia question with associated metadata.
 * It encapsulates the properties and behavior related to a trivia question.
 */
public class Question {

    private final int id; // Unique identifier for the question
    private final Category category; // Category to which the question belongs
    private final Difficulty difficulty; // Difficulty level of the question
    private final String question; // The text of the question

    /**
     * Constructs a new Question instance.
     *
     * @param id The unique identifier for the question
     * @param category The category of the question
     * @param difficulty The difficulty level of the question
     * @param question The text of the question
     */
    public Question(int id, Category category, Difficulty difficulty, String question) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.question = question;
    }

    /**
     * Gets the difficulty level of the question.
     *
     * @return The difficulty level of the question
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the unique identifier of the question.
     *
     * @return The unique identifier of the question
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the category of the question.
     *
     * @return The category of the question
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the text of the question.
     *
     * @return The text of the question
     */
    public String getQuestion() {
        return question;
    }
}
