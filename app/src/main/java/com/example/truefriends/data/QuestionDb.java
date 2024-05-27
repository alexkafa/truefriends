package com.example.truefriends.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.truefriends.Category;
import com.example.truefriends.Difficulty;
import com.example.truefriends.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDb implements IDb<Question>{

    private final DatabaseHelper databaseHelper;

    public QuestionDb(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void add(Question question) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", question.getCategory().name());
        values.put("difficulty", question.getDifficulty().name());
        values.put("question", question.getQuestion());
        values.put("value", question.getValue());
        db.insert("questions", null, values);
        db.close();
    }

    public Question get(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("questions", null, "id=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Question question = new Question(
                    cursor.getInt(0
                    ), Category.valueOf(cursor.getString(1)), Difficulty.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getInt(4));
            cursor.close();
            return question;
        } else {
            return null;
        }
    }

    public List<Question> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        List<Question> questions = new ArrayList<>();
        Cursor cursor = db.query("questions", null, null, null,
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question(
                        cursor.getInt(0
                        ), Category.valueOf(cursor.getString(1)), Difficulty.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getInt(4));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    public List<Question> getAllOfCategory(Category category){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        List<Question> questions = new ArrayList<>();
        Cursor cursor = db.query("questions", null, "category=?", new String[]{category.name()},
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question(
                        cursor.getInt(0
                        ), Category.valueOf(cursor.getString(1)), Difficulty.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getInt(4));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }
    public void update(Question question) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question.getQuestion());
        values.put("category", question.getCategory().name());
        values.put("difficulty", question.getDifficulty().name());
        values.put("value", question.getValue());
        db.update("questions", values, "id=?", new String[]{String.valueOf(question.getId())});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("questions", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
