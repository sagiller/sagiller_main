package com.sagiller.main.entity;

import java.util.Date;

/**
 * Created by sagiller on 2016/10/17.
 */
public class Daily implements Comparable<Daily>{
    private Date date;
    private String dateDisplay;
    private int number;

    public String getDateDisplay() {
        return dateDisplay;
    }

    public void setDateDisplay(String dateDisplay) {
        this.dateDisplay = dateDisplay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.date == null) {
            return false;
        } else {
            return this.date.equals(((Daily)obj).getDate());
        }
    }

    @Override
    public int hashCode() {

        System.out.println("in hash code");
        return date.hashCode(); //TODO
    }
    @Override
    public int compareTo(Daily another) {
        if (this.getDate().compareTo(another.getDate()) >= 0){
            return -1;
        }else{
            return 1;
        }
    }
}
