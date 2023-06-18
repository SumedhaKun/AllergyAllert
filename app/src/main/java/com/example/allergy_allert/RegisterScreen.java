package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {
    Button back_button;
    Button continue_button;
    EditText email_entry;
    EditText password_entry;
    Editable email;
    Editable psw;
    EditText name;
    Editable user_name;
    String confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        name=(EditText) findViewById(R.id.user_name);
        EditText confirm_psw = (EditText) findViewById(R.id.confirm_psw);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    user_name=s;
                }catch(Exception e){}
            }
        });
        back_button=(Button) findViewById(R.id.back_login_r);
        continue_button=(Button) findViewById(R.id.continue_button);
        email_entry =(EditText) findViewById(R.id.EmailAddress);
        email_entry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    email=s;
                } catch (Exception e){}
            }
        });

        password_entry =(EditText) findViewById(R.id.Password);
        password_entry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    psw = s;
                } catch (Exception e) {
                }
            }
        });

        confirm_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    confirm = s.toString();
                } catch (Exception e) {
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (psw.toString().length() < 6) {
                    errorMessage("Your password must be at least 6 characters.");
                } else if (!psw.toString().equals(confirm)) {
                    errorMessage("Passwords don't match");
                } else if (!isValid(email.toString())) {
                    errorMessage("Your email is invalid");
                } else {
                    Intent i = new Intent(RegisterScreen.this, Register2Screen.class);
                    i.putExtra("mail", email.toString());
                    i.putExtra("psw", psw.toString());
                    i.putExtra("name", user_name.toString());
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    public AlertDialog errorMessage(String message) {
        return new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle("Registration Issue")
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
