package com.example.allergy_allert;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DocumentReference ref;
    String user_name;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        user_name="";
        if(user==null){
            startActivity(new Intent(MainActivity.this, LoginScreen.class));
        } else{
            FirebaseFirestore firestore=FirebaseFirestore.getInstance();
            String uID=mAuth.getCurrentUser().getUid();
            ref=firestore.collection("users").document(uID);
            /*
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot doc=task.getResult();
                        if (doc.exists()) {
                            System.out.println("DocumentSnapshot data: " + doc.getData());
                            System.out.println("NaaaaMMe"+doc.getData().get("name"));
                            user_name=doc.getData().get("name").toString();
                        } else {
                            System.out.println("No such document");
                        }
                    }
                    else{
                        System.out.println("get failed with "+task.getException());
                    }
                }
            });

            */
            Intent i=new Intent(MainActivity.this, HomeScreen.class);
            i.putExtra("mail",email);
            i.putExtra("name", user_name);
            startActivity(i);
        }
    }

}

