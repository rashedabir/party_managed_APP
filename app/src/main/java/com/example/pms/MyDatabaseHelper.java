package com.example.pms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "partymanaged.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "guest";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_TYPE = "type";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(username TEXT, password TEXT)");
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_TYPE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS GUEST");
        onCreate(db);
    }

    public boolean insertUser(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long result = mydb.insert("users", null, cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM users WHERE username = ?", new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[] {username, password});
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    void addGuest(String title, String number, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_TYPE, type);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String number, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_TYPE, type);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}


