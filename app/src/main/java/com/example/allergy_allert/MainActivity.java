package com.example.allergy_allert;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;




import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button log_button;
    Button reg_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log_button=(Button) findViewById(R.id.login_button);
        reg_button=(Button) findViewById(R.id.register_button);
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HomeScreen.class);
                startActivity(i);
                finish();
            }
        });
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, RegisterScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
}

