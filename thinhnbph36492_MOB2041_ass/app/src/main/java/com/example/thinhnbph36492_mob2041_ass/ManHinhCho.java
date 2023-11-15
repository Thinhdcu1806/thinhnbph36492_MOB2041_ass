package com.example.thinhnbph36492_mob2041_ass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class ManHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        ImageView ivlogo = findViewById(R.id.ivlogo);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhCho.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}