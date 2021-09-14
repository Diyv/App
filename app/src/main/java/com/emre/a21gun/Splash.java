package com.emre.a21gun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    MyDatabaseHelper myDB;
    String name_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedpref = this.getSharedPreferences("GetName", Context.MODE_PRIVATE);
        name_value = sharedpref.getString(GetName.SHAREDPREF_KEY,"Bos");

        getSupportActionBar().hide();

        myDB = new MyDatabaseHelper(Splash.this);
        Cursor cursor = myDB.ReadAllData();

        if (cursor.getCount() != 0){
            System.out.println("Habit Values Checked");
        }else {
            AddAllHabits();
        }


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(name_value == "Bos"){
                    Intent intent = new Intent(Splash.this, GetName.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }


    private void AddAllHabits() {
        myDB.AddHabit("Sigara", R.drawable.sigara, false);
        myDB.AddHabit("Kalp", R.drawable.kalp, false);
        myDB.AddHabit("Kitap", R.drawable.kitap, false);
        myDB.AddHabit("Saat", R.drawable.saat, false);
        myDB.AddHabit("Kosu", R.drawable.kosu, false);
        myDB.AddHabit("Uyku", R.drawable.uyku, false);
    }
}