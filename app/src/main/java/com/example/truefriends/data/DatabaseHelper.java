package com.example.truefriends.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "questions.db";
    private static DatabaseHelper instance;

    private static final String TABLE_CREATE =
            "CREATE TABLE questions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "category TEXT NOT NULL, " +
                    "difficulty TEXT NOT NULL, " +
                    "question TEXT NOT NULL, " +
                    "value INTEGER NOT NULL);";

    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE questions;");
        db.close();
    }
}
