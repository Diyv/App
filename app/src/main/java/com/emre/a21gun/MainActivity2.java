package com.emre.a21gun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<String> habit_ID,habit_name;
    ArrayList<byte[]> habit_picture;
    ArrayList<Integer> button_vaules_all;
    ArrayList<Integer> button_vaules_column;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        myDB = new MyDatabaseHelper(MainActivity2.this);
        habit_ID = new ArrayList<>();
        habit_name = new ArrayList<>();
        habit_picture = new ArrayList<byte[]>();
        button_vaules_all = new ArrayList<Integer>();
        button_vaules_column = new ArrayList<Integer>();

        DisplayData();

        Intent intent = getIntent();
        int imageview_tag = intent.getIntExtra(MainActivity.INTEND_TAG_ID,0);

        ImageView imageView = findViewById(R.id.imageView12);
        imageView.setImageBitmap(GetBitmap(imageview_tag));

        System.out.println(button_vaules_all);

    }

    public Bitmap GetBitmap(Integer activity_number) {
        byte[] bytesPicture = habit_picture.get(activity_number);
        return BitmapFactory.decodeByteArray(bytesPicture,0,bytesPicture.length);
    }

    private void DisplayData(){
        Cursor cursor = myDB.ReadAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data in Database",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                habit_ID.add(cursor.getString(0));
                habit_name.add(cursor.getString(1));
                habit_picture.add(cursor.getBlob(2));


            }
        }
    }

}