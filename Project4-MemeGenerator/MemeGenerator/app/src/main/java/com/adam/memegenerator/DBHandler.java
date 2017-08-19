package com.adam.memegenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Adam Giaccaglia on 8/18/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    
    // Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "memeDB2";
    
    // User table name
    private static final String USERS_TABLE = "users";

    // User Table Columns names
    private static final String USERS_KEY_ID = "id";
    private static final String USERS_KEY_USERNAME = "username";
    private static final String USERS_KEY_PASSWORD = "password";
    
    
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + "("
        + USERS_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERS_KEY_USERNAME + " TEXT NOT NULL UNIQUE,"
        + USERS_KEY_PASSWORD + " TEXT NOT NULL" + ")";
        try{
            db.execSQL(CREATE_USERS_TABLE);
        }catch(Exception e){
            Log.d("Create table error ", e.toString());
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(db);
    }

    public void addUser(User usr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_KEY_USERNAME, usr.getUsername());
        values.put(USERS_KEY_PASSWORD, usr.getPassword());
        try {
            db.insert(USERS_TABLE, null, values);
        }catch(Exception e){
            Log.d("Insert error: ", e.toString());
        }
        db.close();
    }

    public User getUser(String usrname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectUserByEmail = "SELECT * FROM " + USERS_TABLE + " WHERE " + USERS_KEY_USERNAME + "= ?";
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(selectUserByEmail, new String[] {usrname});
        }catch(Exception e){
            Log.d("SQL Query Error",e.toString());
        }
        cursor.moveToFirst();
        if (cursor.getCount() != 0){
            User usr = new User(cursor.getString(1), cursor.getString(2));
            return usr;
        }else{
            //not found
            User usr = new User("Not Found", "No user");
            return usr;
        }
    }

}
