package com.example.halfchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HalamanHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_home);
    }
    public void clickP2(View v){
        Intent i = new Intent(HalamanHome.this,MainActivity.class);
        startActivity(i);
    }
    public void clickP1(View v){
        Intent i = new Intent(HalamanHome.this,MainActivity.class);
        i.putExtra("COM", 1);
        startActivity(i);
    }

}