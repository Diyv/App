package com.emre.a21gun;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "Day21Database.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "habits_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HABIT_NAME = "habit_name";
    public static final String COLUMN_PICTURE = "habit_picture";

    public static final String COLUMN_DAY1 = "day1";
    public static final String COLUMN_DAY2 = "day2";
    public static final String COLUMN_DAY3 = "day3";
    public static final String COLUMN_DAY4 = "day4";
    public static final String COLUMN_DAY5 = "day5";
    public static final String COLUMN_DAY6 = "day6";
    public static final String COLUMN_DAY7 = "day7";
    public static final String COLUMN_DAY8 = "day8";
    public static final String COLUMN_DAY9 = "day9";
    public static final String COLUMN_DAY10 = "day10";
    public static final String COLUMN_DAY11 = "day11";
    public static final String COLUMN_DAY12 = "day12";
    public static final String COLUMN_DAY13 = "day13";
    public static final String COLUMN_DAY14 = "day14";
    public static final String COLUMN_DAY15 = "day15";
    public static final String COLUMN_DAY16 = "day16";
    public static final String COLUMN_DAY17 = "day17";
    public static final String COLUMN_DAY18 = "day18";
    public static final String COLUMN_DAY19 = "day19";
    public static final String COLUMN_DAY20 = "day20";
    public static final String COLUMN_DAY21 = "day21";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_HABIT_NAME +
                    " TEXT, " + COLUMN_PICTURE + " BLOB, "
                    + COLUMN_DAY1 + " BOOLEAN, " + COLUMN_DAY2 + " BOOLEAN, " + COLUMN_DAY3 + " BOOLEAN, " + COLUMN_DAY4 + " BOOLEAN, " + COLUMN_DAY5 + " BOOLEAN, " + COLUMN_DAY6 + " BOOLEAN, " + COLUMN_DAY7 + " BOOLEAN, " + COLUMN_DAY8 + " BOOLEAN, " + COLUMN_DAY9 + " BOOLEAN, " + COLUMN_DAY10 + " BOOLEAN, " + COLUMN_DAY11 + " BOOLEAN, " + COLUMN_DAY12 + " BOOLEAN, " + COLUMN_DAY13 + " BOOLEAN, " + COLUMN_DAY14 + " BOOLEAN, " + COLUMN_DAY15 + " BOOLEAN, " + COLUMN_DAY16 + " BOOLEAN, " + COLUMN_DAY17 + " BOOLEAN, " + COLUMN_DAY18 + " BOOLEAN, " + COLUMN_DAY19 + " BOOLEAN, " + COLUMN_DAY20 + " BOOLEAN, " + COLUMN_DAY21 + " BOOLEAN);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void AddHabit(String habit, int picture, Boolean default_value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HABIT_NAME, habit);

        //Add Day Values to False
        for (int i = 1; i < 22 ; i++){
            String day = "day" + i;
            cv.put(day,default_value);
        }

        cv.put(COLUMN_DAY1,false);

        //Picture Convertion
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Resources res =  context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,picture);

        bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] bytesImage = outputStream.toByteArray();

        cv.put(COLUMN_PICTURE,bytesImage);

        //Pass Values to Database
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor ReadAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    public boolean CheckDataBaseValuesExists(){
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT habit_name FROM habits_data WHERE _id = 1";
        String query = "SELECT * FROM " + TABLE_NAME;
        boolean check = false;
        ArrayList<String> habit_names = new ArrayList<>();
        String database_string_value = habit_names.get(0);

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);

            while (cursor.moveToNext()){
                habit_names.add(cursor.getString(1));
            }

            if (habit_names.get(0).equals("Sigara")){
                check = true;

            } else if(habit_names.get(0).equals(null)){
                check = false;
            }
        }
        return check;
    }
}
