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

    private static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 2;
    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelper instance;
    private static String DATABASE_PATH;
    private final Context context;

    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        if (context != null) {
            DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
            Log.d("DB", "Database path set to: " + DATABASE_PATH);
            // Ensure the database is copied before it is opened
            copyDatabase();
            getReadableDatabase();
        } else {
            Log.e("DB", "Context is null in DatabaseHelper constructor");
        }
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Not creating tables here as the database is prepopulated and copied from assets
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle upgrades if needed; typically updating the prepopulated DB in assets
        Log.d("DB", "Upgrading database from version " + oldVersion + " to " + newVersion);
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
        try (InputStream input = context.getAssets().open(DATABASE_NAME);
             OutputStream output = new FileOutputStream(DATABASE_PATH)) {

            Log.d("DB", "Copying database to: " + DATABASE_PATH);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            Log.d("DB", "Database copy completed");
        }
    }
}
