package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        TextView textView = findViewById(R.id.textView5);
        String ingredients = getIntent().getStringExtra("ingredients");
        textView.setText(ingredients);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsScreen.this, HomeScreen.class);
                startActivity(i);
                finish();
            }
        });


    }
}
