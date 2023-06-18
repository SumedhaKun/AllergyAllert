package com.example.allergy_allert;

import java.util.ArrayList;
import java.util.Locale;

public class AllergyFood {
    private Boolean isSafe;
    private String foodName;
    private ArrayList<String> problems = new ArrayList<>();

    public String getIngredients() {
        return ingredients;
    }

    private String ingredients;

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;

    }

    public static ArrayList<String> allergies = new ArrayList<>();
    private Integer num_allergies;

    public void setNot_a_sig(String not_a_sig) {
        this.not_a_sig = not_a_sig;
    }

    public String getNot_a_sig() {
        return not_a_sig;
    }

    private String not_a_sig;

    public AllergyFood() {
        problems.clear();
    }


    public Boolean getSafety() {
        isSafe = true;
        for (String allergy : allergies) {
            if (getIngredients().toUpperCase(Locale.ROOT).contains(allergy.toUpperCase(Locale.ROOT)) || getFoodName().toUpperCase(Locale.ROOT).contains(allergy.toUpperCase(Locale.ROOT))) {
                isSafe = false;
                if (!problems.contains(allergy)) {
                    problems.add(allergy);
                }

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

    public ArrayList<String> getAllergies() {
        return allergies;

    }


    @Override
    public String toString() {
        getSafety();
        String problem = "";
        for (String prob : problems) {
            problem += " " + prob;
        }
        String safe;
        if (isSafe) {
            safe = "SAFE. ";
        } else {
            safe = "UNSAFE. Problem: " + problem;
        }
        return foodName.substring(0, 1) + foodName.substring(1).toLowerCase(Locale.ROOT) +
                ": " + safe;
    }
}
