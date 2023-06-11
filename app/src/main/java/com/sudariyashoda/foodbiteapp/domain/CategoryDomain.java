package com.sudariyashoda.foodbiteapp.domain;

public class CategoryDomain {
    private String title; // The title of the category
    private String pic; // The image associated with the category

    // Constructor for creating a CategoryDomain object with a title and an image
    public CategoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    // Getter for retrieving the title of the category
    public String getTitle() {
        return title;
    }

    // Setter for updating the title of the category
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for retrieving the image associated with the category
    public String getPic() {
        return pic;
    }

    // Setter for updating the image associated with the category
    public void setPic(String pic) {
        this.pic = pic;
    }
}
