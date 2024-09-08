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

    // The name of the database file
    private static final String DATABASE_NAME = "questions.db";
    // The version of the database, increment this when you change the database schema
    private static final int DATABASE_VERSION = 2;

    // Singleton instance of DatabaseHelper to ensure only one instance is used throughout the app
    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelper instance;

    // Path where the database will be stored on the device
    private static String DATABASE_PATH;

    // Context of the application using the database helper
    private final Context context;

    // Private constructor for Singleton pattern
    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        if (context != null) {
            // Set the path where the database is or will be stored
            DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
            Log.d("DB", "Database path set to: " + DATABASE_PATH);

            // Copy the prepopulated database from assets to the device if it doesn't already exist
            copyDatabase();

            // Ensure the database is readable, triggers onCreate or onUpgrade if necessary
            getReadableDatabase();
        } else {
            Log.e("DB", "Context is null in DatabaseHelper constructor");
        }
    }

    // Public method to get the Singleton instance of DatabaseHelper
    public static synchronized DatabaseHelper getInstance(Context context) {
        // Create the instance if it doesn't already exist
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This method is not used because the database is prepopulated
        // from the assets folder and copied to the device on first run
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method handles database upgrades if needed
        // For example, you might want to copy a new version of the database from assets
        Log.d("DB", "Upgrading database from version " + oldVersion + " to " + newVersion);
    }

    // Copies the prepopulated database from the assets folder to the device
    private void copyDatabase() {
        // Check if the database already exists on the device
        if (!checkDb()) {
            try {
                // If not, copy the database from the assets folder
                copyDatabaseFromAssets();
                Log.d("DB", "Database copied to " + DATABASE_PATH);
            } catch (Exception e) {
                Log.e("DB", "Error copying database", e);
            }
        } else {
            Log.d("DB", "Database already exists at " + DATABASE_PATH);
        }
    }

    // Checks whether the database already exists on the device
    private boolean checkDb() {
        File dbFile = new File(DATABASE_PATH);
        boolean exists = dbFile.exists();
        Log.d("DB", "Database exists: " + exists);
        return exists;
    }

    // Copies the database file from the assets folder to the device storage
    private void copyDatabaseFromAssets() throws Exception {
        // Use try-with-resources to ensure streams are closed properly
        try (InputStream input = context.getAssets().open(DATABASE_NAME);
             OutputStream output = new FileOutputStream(DATABASE_PATH)) {

            Log.d("DB", "Copying database to: " + DATABASE_PATH);
            byte[] buffer = new byte[1024]; // Buffer size for reading the input stream
            int length;
            // Read the input stream and write it to the output stream
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Ensure all data is written out to the file
            output.flush();
            Log.d("DB", "Database copy completed");
        }
    }
}
