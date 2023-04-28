package com.example.allergy_allert;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivity.this, LoginScreen.class));
        } else{

            Intent i=new Intent(MainActivity.this, HomeScreen.class);
            i.putExtra("mail",user.getEmail());
            startActivity(i);
        }
    }
}

