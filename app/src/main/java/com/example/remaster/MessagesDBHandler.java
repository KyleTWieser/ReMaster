package com.example.remaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

/**
 * Class handles the database functions
 */
public class MessagesDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messagesDB.db";
    //Name of the Table for Messages.
    private static final String TABLE_NAME = "Messages";
    private static final String COLUMN_ID = "MessageId";
    private static final String COLUMN_CONTACT = "ContactName";
    private static final String COLUMN_MESSAGE = "Message";
    //Name of the Table for Time.
    private static final String TABLE_TIME = "TimeStore";
    private static final String COLUMN_TIMEID = "TimeId";
    private static final String COLUMN_HOUR = "Hour";
    private static final String COLUMN_MINUTE = "Minute";

    /**
     * Constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MessagesDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates the tables.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_CONTACT + " TEXT, " + COLUMN_MESSAGE + " TEXT )";
        db.execSQL(CREATE_TABLE);
        String CREATE_TABLE_TIME = "CREATE TABLE " + TABLE_TIME + " (" + COLUMN_TIMEID + " INTEGER PRIMARY KEY, " +  COLUMN_HOUR + " INTEGER, " + COLUMN_MINUTE + " INTEGER)";
        db.execSQL(CREATE_TABLE_TIME);
    }

    /**
     * Updates the database.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String CREATE_TABLE_TIME = "CREATE TABLE " + TABLE_TIME + " (" + COLUMN_TIMEID + " INTEGER PRIMARY KEY, " +  COLUMN_HOUR + " INTEGER, " + COLUMN_MINUTE + " INTEGER)";
        db.execSQL(CREATE_TABLE_TIME);
    }

    /**
     * Used for loading the data from the Messages table.
     * @return
     */
    public Messages loadHandler() {
        Messages result = new Messages();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            result.setID(result_0);
            result.setContactName(result_1);
            result.setMessage(result_2);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * Used for inserting data into the Messages table.
     * @param messageS
     */
    public void addHandler(Messages messageS) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, messageS.getID());
        values.put(COLUMN_CONTACT, messageS.getContactName());
        values.put(COLUMN_MESSAGE, messageS.getMessage());
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = 1", null);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Used for inserting data into the Times table.
     * @param times
     */
    public void addTimeHandler(Times times) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMEID, times.getTimeId());
        values.put(COLUMN_HOUR, times.getHour());
        values.put(COLUMN_MINUTE, times.getMinute());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TIME, COLUMN_TIMEID + " = 1", null);
        db.insert(TABLE_TIME, null, values);
        db.close();
    }

    /**
     * Used for loading data from the Times table.
     * @return
     */
    public Times loadTimeHandler()
    {
        Times times = new Times();
        String query = "SELECT * FROM " + TABLE_TIME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            int result_1 = cursor.getInt(1);
            int result_2 = cursor.getInt(2);
            times.setTimeId(result_0);
            times.setHour(result_1);
            times.setMinute(result_2);
        }
        cursor.close();
        db.close();
        return times;
    }


    /**
     * Used for deleting the the row in the Messages table with the id given.
     * @param id
     */
    public void deleteHandler(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
