package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {
    Button back_button;
    Button continue_button;
    EditText emailbutton;
    EditText passwordbutton;
    Editable email;
    Editable psw;
    EditText name;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        name=(EditText) findViewById(R.id.editTextName);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    user_name=s.toString();
                }catch(Exception e){}
            }
        });
        back_button=(Button) findViewById(R.id.back_login_r);
        continue_button=(Button) findViewById(R.id.continue_button);
        emailbutton=(EditText) findViewById(R.id.EmailAddress);
        emailbutton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    email=s;
                } catch (Exception e){}
            }
        });
        passwordbutton=(EditText) findViewById(R.id.Password);
        passwordbutton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    psw=s;
                } catch (Exception e){}
            }
        });
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
                i.putExtra("mail",email.toString());
                i.putExtra("psw",psw.toString());
                i.putExtra("name",user_name);
                startActivity(i);
                finish();
            }
        });
    }
}
