package com.sagiller.main.entity;

/**
 * Created by sagiller on 2016/10/19.
 */
public class ImageLoading {
    private Long id;
    private String serialNumber;
    private Float loadingMax;
    private Float loadingMin;
    private Float loadingAverage;
    private int loadingTimes;
    private Float drawMax;
    private Float drawMin;
    private Float drawAverage;
    private int drawTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Float getLoadingMax() {
        return loadingMax;
    }

    public void setLoadingMax(Float loadingMax) {
        this.loadingMax = loadingMax;
    }

    public Float getLoadingMin() {
        return loadingMin;
    }

    public void setLoadingMin(Float loadingMin) {
        this.loadingMin = loadingMin;
    }

    public Float getLoadingAverage() {
        return loadingAverage;
    }

    public void setLoadingAverage(Float loadingAverage) {
        this.loadingAverage = loadingAverage;
    }

    public int getLoadingTimes() {
        return loadingTimes;
    }

    public void setLoadingTimes(int loadingTimes) {
        this.loadingTimes = loadingTimes;
    }

    public Float getDrawMax() {
        return drawMax;
    }

    public void setDrawMax(Float drawMax) {
        this.drawMax = drawMax;
    }

    public Float getDrawMin() {
        return drawMin;
    }

    public void setDrawMin(Float drawMin) {
        this.drawMin = drawMin;
    }

    public Float getDrawAverage() {
        return drawAverage;
    }

    public void setDrawAverage(Float drawAverage) {
        this.drawAverage = drawAverage;
    }

    public int getDrawTimes() {
        return drawTimes;
    }

    public void setDrawTimes(int drawTimes) {
        this.drawTimes = drawTimes;
    }
}
