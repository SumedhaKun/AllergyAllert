package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Register2Screen extends AppCompatActivity{
    EditText num_allergies;
    int num_of_allergies=0;
    TextView txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2_screen);
        txt=(TextView) findViewById(R.id.num);
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
                    System.out.println(s);
                    num_of_allergies=Integer.parseInt(s.toString());
                String numAllergies=String.valueOf(num_of_allergies);
                txt.setText(numAllergies);

            }

        });


    }

}
