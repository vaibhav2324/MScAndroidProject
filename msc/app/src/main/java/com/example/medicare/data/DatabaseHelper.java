package com.example.medicare.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="password";
    public static final String COL_4="age";
    public static final String COL_5="mobile";
    public static final String COL_6="gender";
    public static final String TABLE="medicine";
    public static final String COL_7="ID";
    public static final String COL_8="medicineName";
    public static final String COL_9="medicineColor";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)");
        db.execSQL("CREATE TABLE medicine(ID INTEGER PRIMARY KEY AUTOINCREMENT, medicineName TEXT , medicineColor TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

        public long addUser(String user,String password,String age){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("age",age);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return res;

        public long addMedicine(String medicine_name, String medicine_color){
            SQLiteDatabase db=getWritableDatabase();
            ContentValues contentValues1=new ContentValues();
            }


    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=? and " + COL_3 + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count= cursor.getCount();
        cursor.close();
        db.close();
        if(count>0)
            return true;
        else
            return false;
    }
}
