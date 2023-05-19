package com.example.allergy_allert;


public class FoodRow {
    private String foodID;
    private String foodName;
    private String BRCat;
    private String UPC;
    private String BrandOwner;
    private String svgSize;

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getBRCat() {
        return BRCat;
    }

    public void setBRCat(String BRCat) {
        this.BRCat = BRCat;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getBrandOwner() {
        return BrandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        BrandOwner = brandOwner;
    }

    public String getSvgSize() {
        return svgSize;
    }

    public void setSvgSize(String svgSize) {
        this.svgSize = svgSize;
    }

    @Override
    public String toString() {
        return "FoodRow{" +
                "foodID='" + foodID + '\'' +
                ", foodName='" + foodName + '\'' +
                ", BRCat='" + BRCat + '\'' +
                ", UPC=" + UPC +
                ", BrandOwner='" + BrandOwner + '\'' +
                ", svgSize=" + svgSize +
                '}';
    }




}
