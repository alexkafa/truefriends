package com.example.truefriends.data;

import android.content.Context;
import android.util.Log;

import com.example.truefriends.Category;
import com.example.truefriends.Question;

import java.util.List;

public class QuestionRepository {

    private static final String TAG = "QuestionRepository";
    private final QuestionDb questionDb;

    public QuestionRepository(Context context) {
        questionDb = new QuestionDb(context);
    }

    public void addQuestion(Question question) {
        try {
            questionDb.add(question);
            Log.d(TAG, "Added question: " + question.getQuestion());
        } catch (Exception e) {
            Log.e(TAG, "Error adding question: " + question.getQuestion(), e);
        }
    }

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

    public void updateQuestion(Question question) {
        try {
            questionDb.update(question);
            Log.d(TAG, "Updated question with id: " + question.getId());
        } catch (Exception e) {
            Log.e(TAG, "Error updating question with id: " + question.getId(), e);
        }
    }

    public void deleteQuestion(int id) {
        try {
            questionDb.delete(id);
            Log.d(TAG, "Deleted question with id: " + id);
        } catch (Exception e) {
            Log.e(TAG, "Error deleting question with id: " + id, e);
        }
    }
}
