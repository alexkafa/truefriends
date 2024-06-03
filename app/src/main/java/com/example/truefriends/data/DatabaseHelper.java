package com.example.truefriends.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "questions.db";
    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelper instance;
    private static String DATABASE_PATH;
    private final Context context;

    private static final String TABLE_CREATE =
            "CREATE TABLE questions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "category TEXT NOT NULL, " +
                    "difficulty TEXT NOT NULL, " +
                    "question TEXT NOT NULL);";


    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 2); this.context = context;
        DATABASE_PATH = context.getDatabasePath(DATABASE).getPath();
        copyDatabase();
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
    private void copyDatabase() {
        if (!checkDb()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabaseFromAssets();
                Log.d("DB", "Database copied");
            } catch (Exception e) {
                Log.e("DB","Error copying database", e);
            }
        }
    }
    private boolean checkDb(){
        File dbFile = new File(DATABASE_PATH);
        return dbFile.exists();
    }

    private void copyDatabaseFromAssets() throws Exception {
        InputStream input = context.getAssets().open(DATABASE);
        OutputStream output = new FileOutputStream(DATABASE_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }
}
