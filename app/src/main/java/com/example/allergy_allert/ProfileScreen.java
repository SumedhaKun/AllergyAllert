package com.example.allergy_allert;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;




import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileScreen extends AppCompatActivity{
    FirebaseAuth mAuth;
    Button logout;
    String eMail;
    TextView email;
    TextView name;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);
        eMail=getIntent().getStringExtra("email");

        email=(TextView) findViewById(R.id.email);
        email.setText(eMail);
        System.out.println("EHHHE: "+eMail);
        name=(TextView) findViewById(R.id.name);
        user_name=getIntent().getStringExtra("name");
        name.setText(user_name);
        ImageButton icon=(ImageButton) findViewById(R.id.imagebutton);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileScreen.this,HomeScreen.class));
            }
        });

        logout=(Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(ProfileScreen.this,LoginScreen.class));
        });
        mAuth=FirebaseAuth.getInstance();

    }

}
