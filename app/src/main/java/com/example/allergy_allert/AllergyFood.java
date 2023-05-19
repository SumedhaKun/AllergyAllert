package com.example.allergy_allert;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;

public class AllergyFood {
    private Boolean isSafe;
    private String foodName;

    public String getIngredients() {
        return ingredients;
    }

    private String ingredients;
    private ArrayList<String> allergies=new ArrayList<>();
    private Integer num_allergies;

    public void setNot_a_sig(String not_a_sig) {
        this.not_a_sig = not_a_sig;
    }

    public String getNot_a_sig() {
        return not_a_sig;
    }

    private String not_a_sig;
    public AllergyFood(){
        DocumentReference ref;
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        String uID=mAuth.getCurrentUser().getUid();
        ref=firestore.collection("users").document(uID);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if (doc.exists()) {
                        num_allergies=Integer.parseInt(doc.getData().get("number of allergies").toString());
                        for(int i=0; i<num_allergies; i++){
                            String allergy=doc.getData().get("allergy"+(i+1)).toString();
                            allergies.add(allergy);
                        }

                    } else {
                        System.out.println("No such document");
                    }
                }
                else{
                    System.out.println("get failed with "+task.getException());
                }
            }
        });
    }

    public Boolean getSafety(){
        isSafe=true;
        for(String allergy: allergies){
            if(getIngredients().contains(allergy.toUpperCase(Locale.ROOT)) || getFoodName().contains(allergy.toUpperCase(Locale.ROOT))){
                isSafe=false;
            }
            if(getNot_a_sig().contains(allergy)){
                isSafe=false;
            }
        }
        return isSafe;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getAllergies(){
        return allergies;

    }


    @Override
    public String toString() {
        isSafe=getSafety();
        return "AllergyFood{" +
                "isSafe=" + isSafe +
                ", foodName='" + foodName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", allergies=" + allergies +
                ", num_allergies=" + num_allergies +
                '}';
    }
}
