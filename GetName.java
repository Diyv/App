package com.emre.a21gun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetName extends AppCompatActivity {

    String isim = "Bos";
    TextView textView2;
    SharedPreferences sp;

    public static final String SHAREDPREF_KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);

        textView2 = findViewById(R.id.textView2);

        sp = getSharedPreferences("GetName", Context.MODE_PRIVATE);

    }

    public void Onclick(View view){
        isim = textView2.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SHAREDPREF_KEY,isim);
        editor.commit();

        Intent intent = new Intent(GetName.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    
}