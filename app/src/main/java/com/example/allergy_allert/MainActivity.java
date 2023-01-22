package com.example.allergy_allert;

import android.support.v4.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;



import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Painter image = painterResource(R.drawable.logo);
    }
}

