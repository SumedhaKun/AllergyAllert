package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreen extends AppCompatActivity {
    Button back_button;
    Button continue_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        back_button=(Button) findViewById(R.id.back_login_r);
        continue_button=(Button) findViewById(R.id.continue_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterScreen.this, Register2Screen.class);
                startActivity(i);
                finish();
            }
        });
    }
}
