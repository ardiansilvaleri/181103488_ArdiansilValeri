package com.example.sqlte181103488;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name="test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT ,FIRSTNAME TEXT UNIQUE ,LASTNAME TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int ii){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS ");
        onCreate(sqLiteDatabase);

    }
    public void insert_student(String firstname, String Lastname){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME" ,firstname);
        contentValues.put("LASTNAME" ,Lastname);
        this.getWritableDatabase().insertOrThrow( "STUDENTS", "",contentValues);
    }
    public void delete_student(String firstname){
        this.getWritableDatabase().delete( "STUDENTS", "FIRSTNAME='"+firstname+"'",  null);

    }
    public void List_all_students(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1) + " " + cursor.getString(2) + "\n");

        }
    }

    public void update_student(String old_firstname, String new_firstname){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME = '"+new_firstname+"' where FIRSTNAME='"+old_firstname+"'");
    }
}

