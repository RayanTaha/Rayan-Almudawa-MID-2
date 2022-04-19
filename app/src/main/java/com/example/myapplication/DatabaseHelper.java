package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURNAME";
    public static final String COL4 = "NATIONALID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "SURNAME TEXT, " +
                "NATIONALID TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String surname, String nationalid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, surname);
        contentValues.put(COL4, nationalid);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {return false;} else {return true;}
    }

    public Cursor structuredQuery(String productName) {
        SQLiteDatabase db = this.getReadableDatabase(); // No need to write
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL1,
                        COL2, COL3, COL4}, COL2 + "=?",
                new String[]{productName}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor getSpecificResult(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+" Where ID="+ID,null);
        return data;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean deleteData (String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,  COL2 + "like %" +text + "% OR" + COL3 + "like %" + text + "%", null) > 0;

    }
}