package com.example.allergy_allert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    Button back_button;
    Button goButton;
    EditText food;
    Button profile;
    String email;
    String name;
    String entered_food;
    int firstIndex = 0;
    int secondIndex = 1;
    int thirdIndex = 2;

    ArrayList<AllergyFood> allergyFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("mail");
        name = getIntent().getStringExtra("name");

        setContentView(R.layout.home_screen);
        profile = (Button) findViewById(R.id.profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, ProfileScreen.class);
                i.putExtra("email", email);
                i.putExtra("name", name);
                startActivity(i);
                finish();
            }
        });


        food = (EditText) findViewById(R.id.editTextTextFoodName);


        goButton = (Button) findViewById(R.id.goButton);

        TextView txt1 = (TextView) findViewById(R.id.textView);
        TextView txt2 = (TextView) findViewById(R.id.textView3);
        TextView txt3 = (TextView) findViewById(R.id.textView4);

        food.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    entered_food = s.toString();
                } catch (Exception e) {
                }
            }
        });
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button);
        Button button3 = findViewById(R.id.button3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onButton(allergyFoods.get(firstIndex));
                } catch (Error e) {
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onButton(allergyFoods.get(secondIndex));
                } catch (Error e) {
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton(allergyFoods.get(thirdIndex));
            }
        });
        Button moreButton = findViewById(R.id.see_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt1.setText(allergyFoods.get(3).toString());
                    txt2.setText(allergyFoods.get(4).toString());
                    txt3.setText(allergyFoods.get(5).toString());
                    firstIndex = 3;
                    secondIndex = 4;
                    thirdIndex = 5;
                } catch (Exception e) {
                }
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allergyFoods.clear();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL("http://10.0.2.2:1234/" + entered_food.toUpperCase(Locale.ROOT));

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            System.out.println("NOT: " + e);
                        }
                        HttpURLConnection con = null;
                        try {
                            con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("POST");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("NOT: " + e);
                        }


                        DataOutputStream out = null;
                        try {
                            out = new DataOutputStream(con.getOutputStream());
                            out.write("food=TUTTUROSSO GREEN 14.5OZ. NSA ITALIAN DICED TOMATOES".getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            out.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("NOT: " + e);
                        }


                        BufferedReader in = null;
                        try {
                            in = new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                            String inputLine;
                            StringBuffer content = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                            }
                            in.close();
                            if (content.equals("Nothing to Display")) {
                                Toast.makeText(HomeScreen.this, "Nothing to Display", Toast.LENGTH_SHORT);
                            } else {
                                JSONObject contentObject = new JSONObject(String.valueOf(content));
                                Iterator<String> nameItr = contentObject.keys();
                                Map<String, String> outMap = new HashMap<String, String>();
                                while (nameItr.hasNext()) {
                                    String name = nameItr.next();
                                    outMap.put(name, contentObject.getString(name));
                                }
                                for (Map.Entry<String, String> set :
                                        outMap.entrySet()) {
                                    AllergyFood allergyFood = new AllergyFood();

                                    allergyFood.setFoodName(set.getKey());
                                    allergyFood.setIngredients(set.getValue());
                                    allergyFoods.add(allergyFood);
                                }
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("NOT: ");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            txt1.setText(allergyFoods.get(0).toString());
                            txt2.setText(allergyFoods.get(1).toString());
                            txt3.setText(allergyFoods.get(2).toString());
                        } catch (Exception e) {
                        }


                    }
                };
                Thread t = new Thread(r);
                t.start();

            }

        });

    }

    public void onButton(AllergyFood allergyFood) {
        if (!allergyFoods.isEmpty() || entered_food != null) {
            Intent i = new Intent(HomeScreen.this, DetailsScreen.class);
            i.putExtra("ingredients", allergyFood.getIngredients());
            startActivity(i);
            finish();
        } else {
            errorMessage("Error", "There is nothing to see");
        }
    }

    public AlertDialog errorMessage(String title, String msg) {
        return new AlertDialog.Builder(this)
                .setMessage(msg)
                .setTitle(title)
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

}
