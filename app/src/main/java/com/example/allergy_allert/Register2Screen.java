package com.example.allergy_allert;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register2Screen extends AppCompatActivity{
    EditText num_allergies;
    int num_of_allergies=0;
    FirebaseAuth mAuth;
    Button registerButton;
    String eMail;
    String pswd;
    String name;
    HashMap<Integer,Integer> allergens= new HashMap<Integer,Integer>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2_screen);
        eMail=getIntent().getStringExtra("mail");
        pswd=getIntent().getStringExtra("psw");
        name=getIntent().getStringExtra("user_name");

        mAuth=FirebaseAuth.getInstance();
        registerButton=(Button) findViewById(R.id.register2_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("HELLLLLLLo: "+eMail);
                createUser();
                startActivity(new Intent(Register2Screen.this, HomeScreen.class));
                finish();
            }
        });



        num_allergies= (EditText) findViewById(R.id.number_text);
        num_allergies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    try {
                        num_of_allergies=Integer.parseInt(s.toString());
                    }
                    catch (Exception e){}
            }

        });
        Button check=(Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num_of_allergies>8){
                    errorMessage();
                    num_of_allergies=8;
                }
                addInputBoxes();
            }
        });

    }
    public void createUser(){
        String email= eMail;
        String password=pswd;

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register2Screen.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Register2Screen.this, ProfileScreen.class);
                    i.putExtra("mail",email);
                    i.putExtra("name",name);
                    startActivity(i);

                }
                else{
                    Toast.makeText(Register2Screen.this,"Registration error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //continue here
    }
    public void addInputBoxes(){
        allergens.put(1,R.id.allergy1);
        allergens.put(2,R.id.allergy2);
        allergens.put(3,R.id.allergy3);
        allergens.put(4,R.id.allergy4);
        allergens.put(5,R.id.allergy5);
        allergens.put(6,R.id.allergy6);
        allergens.put(7,R.id.allergy7);
        allergens.put(8,R.id.allergy8);
        for(int i=1;i<=8; i++){
            int thatID=allergens.get(i);
            findViewById(thatID).setVisibility(View.INVISIBLE);
        }
        for(int i=1; i<=num_of_allergies; i++){
            int thatID=allergens.get(i);
            findViewById(thatID).setVisibility(View.VISIBLE);
        }
    }
    public AlertDialog errorMessage(){
        return new AlertDialog.Builder(this)
                .setMessage("You may not exceed 8 allergies")
                .setTitle("Limit Exceeded")
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

}
