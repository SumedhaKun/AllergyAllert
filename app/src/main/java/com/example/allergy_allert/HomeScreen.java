package com.example.allergy_allert;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity{
    Button back_button;
    Button goButton;
    EditText food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_screen);
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
        String API_KEY="6abb0fe735msha3963e04a59b15bp187d33jsn84f55bf73007";
        String HOST="edamam-food-and-grocery-database.p.rapidapi.com";
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=food.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(HomeScreen.this);
                String url="https://edamam-food-and-grocery-database.p.rapidapi.com/parser?ingr="+query;
                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Oh no "+error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap headers = new HashMap();
                        headers.put("X-RapidAPI-Key", "6abb0fe735msha3963e04a59b15bp187d33jsn84f55bf73007");
                        return headers;
                    }
                };
                queue.add(request);
        }

    });
}}