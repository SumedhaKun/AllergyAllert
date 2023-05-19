package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    Button log_button;
    Button reg_button;
    FirebaseAuth mAuth;
    EditText log_psw;
    EditText log_email;
    String email;
    String psw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        log_psw=(EditText) findViewById(R.id.LoginPassword);
        log_email=(EditText) findViewById(R.id.EmailAddress);
        log_button=(Button) findViewById(R.id.login_button);
        reg_button=(Button) findViewById(R.id.register_button);
        log_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    psw=s.toString();
                }catch (Exception e){}
            }
        });
        log_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    email=s.toString();
                }catch (Exception e){}
            }
        });

        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                Intent i=new Intent(LoginScreen.this,HomeScreen.class);
                startActivity(i);
                finish();
            }
        });
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email,psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginScreen.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
}
