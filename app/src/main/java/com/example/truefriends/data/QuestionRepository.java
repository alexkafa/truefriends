package com.example.truefriends.data;

import android.content.Context;
import android.util.Log;

import com.example.truefriends.Category;
import com.example.truefriends.Question;

import java.util.List;

/**
 * The QuestionRepository class serves as a higher-level abstraction for managing Question objects.
 * It provides a clean interface for the app to interact with the data layer, using the QuestionDb class
 * to perform the actual database operations.
 */
public class QuestionRepository {

    private static final String TAG = "QuestionRepository";
    private final QuestionDb questionDb;

    /**
     * Constructor that initializes the QuestionDb instance.
     *
     * @param context The application context used to initialize the QuestionDb.
     */
    public QuestionRepository(Context context) {
        questionDb = new QuestionDb(context);
    }

    /**
     * Adds a new Question to the database.
     *
     * @param question The Question object to be added.
     */
    public void addQuestion(Question question) {
        try {
            questionDb.add(question);
            Log.d(TAG, "Added question: " + question.getQuestion());
        } catch (Exception e) {
            Log.e(TAG, "Error adding question: " + question.getQuestion(), e);
        }
    }

    /**
     * Retrieves a Question from the database using its unique ID.
     *
     * @param id The unique ID of the Question to retrieve.
     * @return The Question object if found, otherwise null.
     */
    public Question getQuestion(int id) {
        try {
            Question question = questionDb.get(id);
            if (question != null) {
                Log.d(TAG, "Retrieved question with id: " + id);
            } else {
                Log.w(TAG, "No question found with id: " + id);
            }
            return question;
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving question with id: " + id, e);
            return null;
        }
    }

    /**
     * Retrieves all Question objects from the database.
     *
     * @return A list of all Question objects stored in the database.
     */
    public List<Question> getAllQuestions() {
        try {
            List<Question> questions = questionDb.getAll();
            Log.d(TAG, "Retrieved all questions, count: " + questions.size());
            return questions;
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving all questions", e);
            return null;
        }
    }

    /**
     * Retrieves all Question objects that belong to a specific Category.
     *
     * @param category The Category to filter by.
     * @return A list of Question objects that belong to the specified Category.
     */
    public List<Question> getAllQuestionsOfCategory(Category category) {
        try {
            List<Question> questions = questionDb.getAllOfCategory(category);
            Log.d(TAG, "Retrieved questions for category: " + category + ", count: " + questions.size());
            return questions;
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving questions for category: " + category, e);
            return null;
        }
    }

    /**
     * Updates an existing Question in the database.
     *
     * @param question The Question object with updated values to be stored.
     */
    public void updateQuestion(Question question) {
        try {
            questionDb.update(question);
            Log.d(TAG, "Updated question with id: " + question.getId());
        } catch (Exception e) {
            Log.e(TAG, "Error updating question with id: " + question.getId(), e);
        }
    }

    /**
     * Deletes a Question from the database using its unique ID.
     *
     * @param id The unique ID of the Question to delete.
     */
    public void deleteQuestion(int id) {
        try {
            questionDb.delete(id);
            Log.d(TAG, "Deleted question with id: " + id);
        } catch (Exception e) {
            Log.e(TAG, "Error deleting question with id: " + id, e);
        }
    }
}
