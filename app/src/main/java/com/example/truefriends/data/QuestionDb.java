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

    public QuestionDb(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void add(Question question) {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("category", question.getCategory().name());
            values.put("difficulty", question.getDifficulty().name());
            values.put("question", question.getQuestion());
            db.insert("questions", null, values);
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public Question get(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("questions", null, "id=?", new String[]{String.valueOf(id)},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return new Question(
                        cursor.getInt(0),
                        Category.valueOf(cursor.getString(1)),
                        Difficulty.valueOf(cursor.getString(2)),
                        cursor.getString(3)
                );
            } else {
                return null;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public List<Question> getAll() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = databaseHelper.getReadableDatabase();
            List<Question> questions = new ArrayList<>();
            cursor = db.query("questions", null, null, null, null, null, null);
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
            return questions;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public List<Question> getAllOfCategory(Category category) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Question> questions = new ArrayList<>();
        try {
            db = databaseHelper.getReadableDatabase();
            String categoryName = category.name();
            Log.d("QuestionDb", "Querying for category: " + categoryName);
            cursor = db.query("questions", null, "category=?", new String[]{categoryName}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
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
            Log.d("QuestionDb", "Fetched " + questions.size() + " questions for category " + categoryName);
        } catch (Exception e) {
            Log.e("QuestionDb", "Error fetching questions for category " + category, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return questions;
    }



    public void update(Question question) {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("question", question.getQuestion());
            values.put("category", question.getCategory().name());
            values.put("difficulty", question.getDifficulty().name());
            db.update("questions", values, "id=?", new String[]{String.valueOf(question.getId())});
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            db.delete("questions", "id=?", new String[]{String.valueOf(id)});
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }
}
