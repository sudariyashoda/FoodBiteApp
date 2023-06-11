package com.sudariyashoda.foodbiteapp.domain;

public class FastDeliveryDomain {

    // Declare private instance variables
    private String Title; // Title of the fast delivery domain
    private String pic; // Picture associated with the fast delivery domain
    private Double star; // Star rating of the fast delivery domain
    private int time; // Time taken for delivery

    // Constructor with parameters to initialize the instance variables
    public FastDeliveryDomain(String Title, String pic) {
        this.Title = Title;
        this.pic = pic;
        this.star = star; // Note: It seems like there is a missing parameter here
        this.time = time; // Note: It seems like there is a missing parameter here
    }

    // Getter for the Title
    public String getTitle() {
        return Title;
    }

    // Setter for the Title
    public void setTitle(String Title) {
        this.Title = Title;
    }

    // Getter for the pic
    public String getPic() {
        return pic;
    }

    // Setter for the pic
    public void setPic(String pic) {
        this.pic = pic;
    }

    // Getter for the star rating
    public Double getStar() {
        return star;
    }

    // Setter for the star rating
    public void setStar(Double star) {
        this.star = star;
    }

    // Getter for the delivery time
    public int getTime() {
        return time;
    }

    // Setter for the delivery time
    public void setTime(int time) {
        this.time = time;
    }
}
