package com.sagiller.main.entity;

/**
 * Created by sagiller on 2016/10/19.
 */
public class ImageLoadingAverage {
    private int platform;
    private float loadingAverage;
    private float drawAverage;
    private int loadingTimes;
    private int drawTimes;

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public float getLoadingAverage() {
        return loadingAverage;
    }

    public void setLoadingAverage(float loadingAverage) {
        this.loadingAverage = loadingAverage;
    }

    public float getDrawAverage() {
        return drawAverage;
    }

    public void setDrawAverage(float drawAverage) {
        this.drawAverage = drawAverage;
    }

    public int getLoadingTimes() {
        return loadingTimes;
    }

    public void setLoadingTimes(int loadingTimes) {
        this.loadingTimes = loadingTimes;
    }

    public int getDrawTimes() {
        return drawTimes;
    }

    public void setDrawTimes(int drawTimes) {
        this.drawTimes = drawTimes;
    }
}
