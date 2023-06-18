package com.example.allergy_allert;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register2Screen extends AppCompatActivity{
    EditText num_allergies;
    int num_of_allergies=0;
    FirebaseAuth mAuth;
    Button registerButton;
    String eMail;
    String pswd;
    String uID;
    FirebaseFirestore firestore;
    String user_name;
    HashMap<Integer,Integer> allergens= new HashMap<Integer,Integer>();
    String[] alls=new String[8];


    public ArrayList<String> getListOfAllergens(){
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<num_of_allergies;i++){
            list.add("");
            list.set(i,alls[i]);
        }
        return list;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2_screen);
        //Connecting UI values
        eMail=getIntent().getStringExtra("mail");
        pswd=getIntent().getStringExtra("psw");
        user_name=getIntent().getStringExtra("name");
        registerButton=(Button) findViewById(R.id.register2_button);

        //Initializing Firebase and Firestore
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        Button back=(Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register2Screen.this, RegisterScreen.class);
                startActivity(i);
            }
        });

        //Button to Register
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list=getListOfAllergens();
                createUser(list);
                startActivity(new Intent(Register2Screen.this, HomeScreen.class));
                finish();
            }
        });


        EditText allergy1=(EditText) findViewById(R.id.allergy1);
        EditText allergy2=(EditText) findViewById(R.id.allergy2);
        EditText allergy3=(EditText) findViewById(R.id.allergy3);
        EditText allergy4=(EditText) findViewById(R.id.allergy4);
        EditText allergy5=(EditText) findViewById(R.id.allergy5);
        EditText allergy6=(EditText) findViewById(R.id.allergy6);
        EditText allergy7=(EditText) findViewById(R.id.allergy7);
        EditText allergy8=(EditText) findViewById(R.id.allergy8);

        allergy1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[0]=s.toString();
            }
        });

        allergy2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[1]=s.toString();
            }
        });

        allergy3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[2]=s.toString();
            }
        });

        allergy4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[3]=s.toString();
            }
        });

        allergy5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[4]=s.toString();
            }
        });

        allergy6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[5]=s.toString();
            }
        });

        allergy7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[6]=s.toString();
            }
        });

        allergy8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                alls[7]=s.toString();
            }
        });



        num_allergies= (EditText) findViewById(R.id.number_text);
        num_allergies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                    try {
                        num_of_allergies=Integer.parseInt(s.toString());
                    }
                    catch (Exception e){}
            }});
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

    public void createUser(List<String> allergens){
        String email= eMail;
        String password=pswd;

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register2Screen.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                    uID=mAuth.getCurrentUser().getUid();
                    DocumentReference ref=firestore.collection("users").document(uID);
                    Map<String,Object> user=new HashMap<>();
                    user.put("name",user_name);
                    user.put("email",email);
                    int c=0;
                    user.put("number of allergies",allergens.size());
                    for (String allergen:allergens){
                        c++;
                        user.put("allergy"+c,allergen);
                    }
                    ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            System.out.println("SUCCESS, profile created for "+uID);
                        }
                    });
                    Intent i = new Intent(Register2Screen.this, MainActivity.class);
                    i.putExtra("mail",email);
                    i.putExtra("name",user_name);
                    startActivity(i);

                }
                else {
                    System.out.println("DIDNT WORK");
                    Toast.makeText(Register2Screen.this, "Registration error", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

