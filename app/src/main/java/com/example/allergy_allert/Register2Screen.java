package com.example.allergy_allert;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Register2Screen extends AppCompatActivity{
    EditText num_allergies;
    int num_of_allergies=0;
    HashMap<Integer,Integer> allergens= new HashMap<Integer,Integer>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register2_screen);
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
