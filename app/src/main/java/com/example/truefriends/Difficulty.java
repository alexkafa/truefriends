package com.example.truefriends;

/**
 * The Difficulty enum represents the different difficulty levels of questions in the application.
 * Each difficulty level is associated with a specific number of points, indicating its complexity.
 */
public enum Difficulty {
    EASY(2),   // Easy difficulty level, associated with 2 points.
    MEDIUM(3), // Medium difficulty level, associated with 3 points.
    HARD(5);   // Hard difficulty level, associated with 5 points.

    private int points;

    /**
     * Constructor for the Difficulty enum.
     *
     * @param points The number of points associated with this difficulty level.
     */
    Difficulty(int points) {
        this.points = points;
    }

    /**
     * Gets the number of points associated with this difficulty level.
     *
     * @return The points associated with this difficulty level.
     */
    public int getPoints() {
        return points;
    }
}
