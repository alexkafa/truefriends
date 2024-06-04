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

    private static final String DATABASE_NAME = "questions";
    private static final int DATABASE_VERSION = 2;
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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        assert context != null;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        // Ensure the database path is initialized
        getReadableDatabase();
        copyDatabase();
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
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
        db.execSQL("DROP TABLE IF EXISTS questions;");
        onCreate(db);
    }

    private void copyDatabase() {
        if (!checkDb()) {
            try {
                copyDatabaseFromAssets();
                Log.d("DB", "Database copied to " + DATABASE_PATH);
            } catch (Exception e) {
                Log.e("DB", "Error copying database", e);
            }
        } else {
            Log.d("DB", "Database already exists at " + DATABASE_PATH);
        }
    }


    private boolean checkDb() {
        File dbFile = new File(DATABASE_PATH);
        boolean exists = dbFile.exists();
        Log.d("DB", "Database exists: " + exists);
        return exists;
    }

    private void copyDatabaseFromAssets() throws Exception {
        InputStream input = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH;
        Log.d("DB", "Copying database to: " + outFileName); // Add this line for logging
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
        Log.d("DB", "Database copy completed");
    }

}
