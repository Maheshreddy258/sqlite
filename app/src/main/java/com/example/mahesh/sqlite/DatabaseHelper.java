package com.example.mahesh.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public static final String DBCREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT,"
                    + COL_4 + " INTEGER"
                    + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBCREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IFEXISTS" + DBCREATE_TABLE);

        onCreate(db);


    }

    public boolean insertData(String name , String surname , String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME, null ,contentValues);

        if (result == -1){
            return false;
        }
        else return true;

    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME,null );
        return cursor;

    }


    public boolean updatedata(String id ,String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
         db.update(TABLE_NAME,contentValues,"ID = ?", new String[]{id});
         return true;

    }

          public Integer deletedata(String id){
              SQLiteDatabase db = this.getWritableDatabase();
              return db.delete(TABLE_NAME, "ID=?",new String[]{id});

          }

}
