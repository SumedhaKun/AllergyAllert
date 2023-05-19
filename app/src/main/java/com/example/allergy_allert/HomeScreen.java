package com.example.allergy_allert;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity{
    Button back_button;
    Button goButton;
    EditText food;
    Button profile;
    String email;
    String name;
    String entered_food;
    ArrayList<FoodRow> rows=new ArrayList<>();
    ArrayList<BrandedFoods> foods=new ArrayList<>();
    ArrayList<AllergyFood> allergyFoods=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email=getIntent().getStringExtra("mail");
        name=getIntent().getStringExtra("name");

        setContentView(R.layout.home_screen);
        profile=(Button) findViewById(R.id.profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeScreen.this,ProfileScreen.class);
                i.putExtra("email",email);
                i.putExtra("name",name);
                startActivity(i);
                finish();
            }
        });


        food=(EditText) findViewById(R.id.editTextTextFoodName);
        back_button=(Button) findViewById(R.id.back_login);
        goButton=(Button) findViewById(R.id.goButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hello");
                Intent i=new Intent(HomeScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        food.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    entered_food=s.toString();
                } catch (Exception e){}
            }
        });



        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        allergyFoods.clear();
                        foods.clear();
                        rows.clear();
                        System.out.println("ENTERED FOOD"+entered_food);
                        getEnteredFoodID(entered_food);
                        getIngredients(rows);
                        for(AllergyFood all: allergyFoods){
                            System.out.println(all);
                        }


                    }
                };
                Thread t = new Thread(r);
                t.start();

        }

    });

}

    public void getEnteredFoodID(String food){

        InputStream is=getResources().openRawResource(R.raw.food_id);
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        String line;
        try{
            reader.readLine();
            int j=0;
            while((line=reader.readLine())!=null){

                //split by comma
                String[] tokens=line.split(",");
                FoodRow row=new FoodRow();
                row.setFoodID(tokens[0]);
                row.setFoodName(tokens[1]);
                row.setBRCat(tokens[2]);
                row.setUPC(tokens[3]);
                row.setBrandOwner(tokens[4]);
                row.setSvgSize(tokens[5]);

                if(row.getFoodName().contains(food.toUpperCase(Locale.ROOT))){
                    j++;
                    rows.add(row);
                }
                if(j>=5){
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("ERROR"+e);
        }

    }

    public void getIngredients(ArrayList<FoodRow> rows){

        InputStream is=getResources().openRawResource(R.raw.branded_food);
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        String line;
        try{
            reader.readLine();
            while((line=reader.readLine())!=null){

                //split by comma
                String[] tokens=line.split(",");
                BrandedFoods food=new BrandedFoods();
                food.setFoodID(tokens[0]);
                food.setBrand_owner(tokens[1]);
                food.setUpc(tokens[4]);
                food.setIngredients(tokens[5]);
                food.setNot_a_significant(tokens[6]);
                for (FoodRow row:rows){
                    if(row.getUPC().equals(food.getUpc())){
                        foods.add(food);
                        AllergyFood allergyFood=new AllergyFood();
                        allergyFood.setFoodName(row.getFoodName());
                        allergyFood.setIngredients(food.getIngredients());
                        allergyFood.setNot_a_sig(food.getNot_a_significant());
                        allergyFoods.add(allergyFood);
                    }
                }

            }

        }catch (Exception e){
            System.out.println("ERROR"+e);
        }
    }


}
