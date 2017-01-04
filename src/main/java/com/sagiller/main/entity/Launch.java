package com.sagiller.main.entity;

import java.util.Date;

/**
 * Created by sagiller on 2016/10/20.
 */
public class Launch {
    private int id;
    private String serialNumber;
    private Date launchTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }
}
