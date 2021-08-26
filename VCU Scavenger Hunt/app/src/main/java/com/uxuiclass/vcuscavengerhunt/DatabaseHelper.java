package com.uxuiclass.vcuscavengerhunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class interacts with the database on the user's phone. It is used to store all
 * of the questions, the user's status on those questions, and the user's personal 
 * information.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // All of the database table layout info
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "hunt_data";
    private static final String col0 = "ID";
    private static final String col1 = "category";
    private static final String col2 = "locationTitle";
    private static final String col3 = "instuctions";
    private static final String col4 = "image";
    private static final String col5 = "question";
    private static final String col6 = "answer";
    private static final String col7 = "pointValue";
    private static final String col8 = "answered";

    /**
     * <p>This is the constructor for DatabaseHelper and it builds the database.</p>
     *
     * <h1>NOTE: Do not change version here!</h1>
     *
     * @param context - this current state of the app
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + col0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col1 + " TEXT, " + col2 + " TEXT, " + col3 + " TEXT, " + col4 + " TEXT, " + col5 + " TEXT, " + col6 + " TEXT, " + col7 + " INTEGER, " + col8 + " BOOLEAN)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * This method will be used to add an item to the table.
     *
     * @param cat           - The category that they place falls under
     * @param location      - The place in question
     * @param instructions  - How to get to the place in quesion
     * @param imageRef      - The picture of the place
     * @param question      - The question about the place
     * @param ans           - A string of possible correct answers
     * @param points        - How much the question is worth
     * @param isSolved      - Whether or not the question has been answered or not
     * @return True if everything is added properly. False on error.
     */
    protected boolean addData(String cat, String location, String instructions, String imageRef, String question, String ans, int points, boolean isSolved) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Putting the content together to go into the table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, cat);
        contentValues.put(col2, location);
        contentValues.put(col3, instructions);
        contentValues.put(col4, imageRef);
        contentValues.put(col5, question);
        contentValues.put(col6, ans);
        contentValues.put(col7, points);
        contentValues.put(col8, isSolved);

        // Logging for debugging
        Log.d(TAG, "addData: Adding " + cat + ", " + location + ", " + instructions + ", " + imageRef + ", " + question + ", " + ans + ", " + points + ", and " + isSolved + " to " + TABLE_NAME + ".");

        // To see if everyting was added correctly or not
        long result = db.insert(TABLE_NAME, null, contentValues);

        // If there was an error, return false
        // Otherwise, return true
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method will be used to update a table entry when a user submits an answer. On an
     * incorrect submission, the question value will be lowered. On a correct submission, the
     * question will be marked as solved.
     *
     * @param id            - Helps keep the database organized
     * @param cat           - The category that they place falls under
     * @param location      - The place in question
     * @param instructions  - How to get to the place in quesion
     * @param imageRef      - The picture of the place
     * @param question      - The question about the place
     * @param ans           - A string of possible correct answers
     * @param points        - How much the question is worth
     * @param isSolved      - Whether or not the question has been answered or not
     * @return True if everything updates properly. False on error.
     */
    protected boolean updateData(int id, String cat, String location, String instructions, String imageRef, String question, String ans, int points, boolean isSolved){
        SQLiteDatabase db = this.getWritableDatabase();

        // Putting the new content together to go into the table
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, cat);
        contentValues.put(col2, location);
        contentValues.put(col3, instructions);
        contentValues.put(col4, imageRef);
        contentValues.put(col5, question);
        contentValues.put(col6, ans);
        contentValues.put(col7, points);
        contentValues.put(col8, isSolved);

        // Commanding the update
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {Integer.toString(id)});

        // Everything went well if we get to this point
        return true;
    }

    /**
     * This method is meant to be able to delete a row. It has not been tested.
     *
     * @param locValue - The location we want to delete
     */
    protected void delete(String locValue) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = col2 + "=" + locValue;
        db.delete(TABLE_NAME, whereClause, null);
    }

    /**
     * This method allows the app to look through the database.
     *
     * @return A cursor to go through the database with
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}
