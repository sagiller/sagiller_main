package com.sagiller.main.entity;

import java.util.List;

/**
 * Created by sagiller on 2016/10/19.
 */
public class UploadData {
    private String serialNumber;
    private List<Long> times;
    private ImageLoading imageLoading;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Long> getTimes() {
        return times;
    }

    public void setTimes(List<Long> times) {
        this.times = times;
    }

    public ImageLoading getImageLoading() {
        return imageLoading;
    }

    public void setImageLoading(ImageLoading imageLoading) {
        this.imageLoading = imageLoading;
    }
}
