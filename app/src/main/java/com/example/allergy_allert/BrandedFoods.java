package com.example.allergy_allert;

public class BrandedFoods {
    private String foodID;
    private String brand_owner;
    private String brand_name;
    private String sub_name;
    private String upc;
    private String ingredients;

    public void setNot_a_significant(String not_a_significant) {
        this.not_a_significant = not_a_significant;
    }

    public String getNot_a_significant() {
        return not_a_significant;
    }

    private String not_a_significant;

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getBrand_owner() {
        return brand_owner;
    }

    public void setBrand_owner(String brand_owner) {
        this.brand_owner = brand_owner;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }





}
