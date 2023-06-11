package com.sudariyashoda.foodbiteapp.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Hours {

    // Variables to store opening hours for each day of the week
    String Sunday;
    String Monday;
    String Tuesday;
    String Wednesday;
    String Thursday;
    String Friday;
    String Saturday;

    // Getter and Setter methods for each day's opening hours

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    // Method to get the opening hours for the current day
    public String getTodaysHours() {
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        // Format the date to get the day of the week
        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        // Return the opening hours based on the current day
        switch (day) {
            case "Sunday":
                return this.Sunday;
            case "Monday":
                return this.Monday;
            case "Tuesday":
                return this.Tuesday;
            case "Wednesday":
                return this.Wednesday;
            case "Thursday":
                return this.Thursday;
            case "Friday":
                return this.Friday;
            case "Saturday":
                return this.Saturday;
            default:
                return this.Sunday;
        }
    }
}
