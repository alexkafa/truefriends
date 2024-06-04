package com.example.truefriends.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.truefriends.Category;
import com.example.truefriends.Difficulty;
import com.example.truefriends.Question;

public class DBInitializer {

    private final QuestionRepository questionRepository;
    private static final String PREFS_NAME = "DB_PREFS";
    private static final String DB_INITIALIZED_KEY = "DB_INITIALIZED";

    public DBInitializer(Context context) {
        this.questionRepository = new QuestionRepository(context);
    }

    public void initializeDatabase(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isDbInitialized = prefs.getBoolean(DB_INITIALIZED_KEY, false);

        if (!isDbInitialized) {
            Log.d("DBInitializer", "Initializing database with sample questions");
            try {
                addQuestionsForCategory(Category.ANCIENT_HISTORY);
                addQuestionsForCategory(Category.NAMES);
                addQuestionsForCategory(Category.DATES);
                addQuestionsForCategory(Category.HOBBIES);
                addQuestionsForCategory(Category.TRUE_FRIEND);
                addQuestionsForCategory(Category.HOPES_DREAMS);
                addQuestionsForCategory(Category.GOSSIP);
                addQuestionsForCategory(Category.BINARY_QUESTIONS);
                addQuestionsForCategory(Category.TOP_5);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(DB_INITIALIZED_KEY, true);
                editor.apply();
                Log.d("DBInitializer", "Database initialization complete");
            } catch (Exception e) {
                Log.e("DBInitializer", "Error during database initialization", e);
            }
        } else {
            Log.d("DBInitializer", "Database already initialized");
        }
    }

    private void addQuestionsForCategory(Category category) {
        for (int i = 1; i <= 10; i++) {
            Question question = new Question(
                    category.ordinal() * 10 + i,
                    category,
                    Difficulty.EASY, // You can change this to vary the difficulty
                    "Sample question " + i + " for " + category.name()
            );
            try {
                questionRepository.addQuestion(question);
                Log.d("DBInitializer", "Added question: " + question.getQuestion());
            } catch (Exception e) {
                Log.e("DBInitializer", "Error adding question: " + question.getQuestion(), e);
            }
        }
    }
}
