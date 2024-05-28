package com.example.truefriends.data;

import android.content.Context;

import com.example.truefriends.Category;
import com.example.truefriends.Question;

import java.util.List;

public class QuestionRepository {

    private final QuestionDb questionDb;

    public QuestionRepository(Context context) {
        questionDb = new QuestionDb(context);
    }

    public void addQuestion(Question question) {
        questionDb.add(question);
    }

    public Question getQuestion(int id) {
        return questionDb.get(id);
    }

    public List<Question> getAllQuestions() {
        return questionDb.getAll();
    }

    public List<Question> getAllQuestionsOfCategory(Category category) {
        return questionDb.getAllOfCategory(category);
    }

    public void updateQuestion(Question question) {
        questionDb.update(question);
    }

    public void deleteQuestion(int id) {
        questionDb.delete(id);
    }
}
