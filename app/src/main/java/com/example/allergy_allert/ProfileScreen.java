package com.example.allergy_allert;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;

public class ProfileScreen extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logout;
    TextView email;
    String mail;
    TextView name;
    String user_name;
    ArrayList<String> allergies = new ArrayList<>();
    //UserInformation uI=new UserInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);
        /*
        eMail=uI.getEmail();
        System.out.println("emailll "+eMail);
        email=(TextView) findViewById(R.id.email);
        System.out.println(eMail);
        email.setText(eMail);
        user_name=uI.getName();
        name=(TextView) findViewById(R.id.name);
        name.setText(user_name);
        ArrayList<String> allergies=uI.getAllergies();
        TextView allergens=(TextView) findViewById(R.id.allergens);

         */
        email = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById(R.id.name);
        TextView allergens = (TextView) findViewById(R.id.allergens);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String uID = mAuth.getCurrentUser().getUid();
        DocumentReference ref = firestore.collection("users").document(uID);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        mail = doc.getData().get("email").toString();

                        user_name = doc.getData().get("name").toString();
                        name.setText("Name: " + user_name);
                        email.setText("Email: " + mail);
                        System.out.println("DATA below");
                        System.out.println(email);
                        System.out.println(name);
                        int num_allergies = Integer.parseInt(doc.getData().get("number of allergies").toString());
                        for (int i = 0; i < num_allergies; i++) {
                            String allergy = doc.getData().get("allergy" + (i + 1)).toString();
                            allergies.add(allergy.substring(0, 1).toUpperCase(Locale.ROOT) + allergy.substring(1));
                        }
                        String all = "Allergies: ";
                        for (int i = 0; i < allergies.size(); i++) {
                            if (i == 0) {
                                all += allergies.get(0);
                            } else {
                                all += ", " + allergies.get(i);
                            }
                        }
                        allergens.setText(all);

                    } else {
                        System.out.println("No such document");
                        mail = "";

                    }
                } else {
                    System.out.println("get failed with " + task.getException());
                    mail = "";
                }
            }
        });


        //allergens.setText(allergies.toString());

        Button icon = (Button) findViewById(R.id.HomeButton);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileScreen.this, HomeScreen.class));
            }
        });

        logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(ProfileScreen.this, LoginScreen.class));
        });
        mAuth = FirebaseAuth.getInstance();

    }

}
