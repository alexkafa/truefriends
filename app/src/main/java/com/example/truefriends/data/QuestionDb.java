package com.example.truefriends.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.truefriends.Category;
import com.example.truefriends.Difficulty;
import com.example.truefriends.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDb implements IDb<Question> {

    private final DatabaseHelper databaseHelper;

    public QuestionDb(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public void add(Question question) {
        try (SQLiteDatabase db = databaseHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("category", question.getCategory().name());
            values.put("difficulty", question.getDifficulty().name());
            values.put("question", question.getQuestion());
            db.insert("questions", null, values);
            Log.d("QuestionDb", "Added question: " + question.getQuestion());
        } catch (Exception e) {
            Log.e("QuestionDb", "Error adding question: " + question.getQuestion(), e);
        }
    }

    @Override
    public Question get(int id) {
        Question question = null;
        try (SQLiteDatabase db = databaseHelper.getReadableDatabase();
             Cursor cursor = db.query("questions", null, "id=?", new String[]{String.valueOf(id)}, null, null, null)) {

            if (cursor != null && cursor.moveToFirst()) {
                question = new Question(
                        cursor.getInt(0),
                        Category.valueOf(cursor.getString(1)),
                        Difficulty.valueOf(cursor.getString(2)),
                        cursor.getString(3)
                );
                Log.d("QuestionDb", "Fetched question with id: " + id);
            } else {
                Log.w("QuestionDb", "No question found with id: " + id);
            }
        } catch (Exception e) {
            Log.e("QuestionDb", "Error fetching question with id: " + id, e);
        }
        return question;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        try (SQLiteDatabase db = databaseHelper.getReadableDatabase();
             Cursor cursor = db.query("questions", null, null, null, null, null, null)) {

            if (cursor.moveToFirst()) {
                do {
                    Question question = new Question(
                            cursor.getInt(0),
                            Category.valueOf(cursor.getString(1)),
                            Difficulty.valueOf(cursor.getString(2)),
                            cursor.getString(3)
                    );
                    questions.add(question);
                } while (cursor.moveToNext());
            }
            Log.d("QuestionDb", "Fetched all questions, count: " + questions.size());
        } catch (Exception e) {
            Log.e("QuestionDb", "Error fetching all questions", e);
        }
        return questions;
    }

    @Override
    public List<Question> getByField(String fieldName, String value) {
        List<Question> questions = new ArrayList<>();
        Log.d("QuestionDb", "Querying for field: " + fieldName + " with value: " + value);

        try (SQLiteDatabase db = databaseHelper.getReadableDatabase();
             Cursor cursor = db.query("questions", null, fieldName + "=?", new String[]{value}, null, null, null)) {

            if (cursor.moveToFirst()) {
                do {
                    Question question = new Question(
                            cursor.getInt(0),
                            Category.valueOf(cursor.getString(1)),
                            Difficulty.valueOf(cursor.getString(2)),
                            cursor.getString(3)
                    );
                    questions.add(question);
                } while (cursor.moveToNext());
            }
            Log.d("QuestionDb", "Fetched " + questions.size() + " questions for field " + fieldName + " with value " + value);
        } catch (Exception e) {
            Log.e("QuestionDb", "Error fetching questions for field " + fieldName + " with value " + value, e);
        }
        return questions;
    }

    @Override
    public void update(Question question) {
        try (SQLiteDatabase db = databaseHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("question", question.getQuestion());
            values.put("category", question.getCategory().name());
            values.put("difficulty", question.getDifficulty().name());
            int rows = db.update("questions", values, "id=?", new String[]{String.valueOf(question.getId())});
            Log.d("QuestionDb", "Updated question with id: " + question.getId() + ", affected rows: " + rows);
        } catch (Exception e) {
            Log.e("QuestionDb", "Error updating question with id: " + question.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (SQLiteDatabase db = databaseHelper.getWritableDatabase()) {
            int rows = db.delete("questions", "id=?", new String[]{String.valueOf(id)});
            Log.d("QuestionDb", "Deleted question with id: " + id + ", affected rows: " + rows);
        } catch (Exception e) {
            Log.e("QuestionDb", "Error deleting question with id: " + id, e);
        }
    }

    // Specific method for QuestionDb
    public List<Question> getAllOfCategory(Category category) {
        return getByField("category", category.name());
    }
}
