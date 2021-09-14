package com.emre.a21gun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String INTEND_TAG_ID = "Intend Tag Number";

    MyDatabaseHelper myDB;
    ArrayList<String> habit_ID,habit_name;
    ArrayList<byte[]> habit_picture;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("GetName", Context.MODE_PRIVATE);

        getSupportActionBar().hide();

        myDB = new MyDatabaseHelper(MainActivity.this);
        habit_ID = new ArrayList<>();
        habit_name = new ArrayList<>();
        habit_picture = new ArrayList<byte[]>();

        DisplayData();
        SetUserName();
        SetImageViews();

    }

    private void SetUserName() {
        TextView textView_name = findViewById(R.id.textView3);
        textView_name.setText(sp.getString(GetName.SHAREDPREF_KEY,"Bos"));
    }

    private void SetImageViews() {

        ImageView imageView1 = findViewById(R.id.imageView);
        imageView1.setImageBitmap(GetBitmap(0));
        imageView1.setTag(0);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageBitmap(GetBitmap(1));
        imageView1.setTag(1);

        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setImageBitmap(GetBitmap(2));
        imageView1.setTag(2);

        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4.setImageBitmap(GetBitmap(3));
        imageView1.setTag(3);

        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setImageBitmap(GetBitmap(4));
        imageView1.setTag(4);

        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setImageBitmap(GetBitmap(5));
        imageView1.setTag(5);
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

    public void ClickIntentActivity2(View view){
        int tagValue = Integer.parseInt((view.getTag().toString()));

        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra(INTEND_TAG_ID,tagValue);
        startActivity(intent);
    }

}