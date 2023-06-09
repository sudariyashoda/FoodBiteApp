package com.sudariyashoda.foodbiteapp.domain;
public class FastDeliveryDomain {
    private String Title;
    private String pic;
    private Double star;
    private int time;
    public FastDeliveryDomain(String Title, String pic) {
        this.Title = Title;
        this.pic = pic;
        this.star = star;
        this.time = time;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public Double getStar() {
        return star;
    }
    public void setStar(Double star) {
        this.star = star;
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
