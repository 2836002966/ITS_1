package com.example.administrator.its.entity;

/**
 * Created by Administrator on 2018/11/4.
 */

public class EnvironmentalBean {
    private int temperature;
    private int humidity;
    private int lightIntensity;
    private int co2;
    private int pm;
    private int status;



    public int getHumidity() {
        return humidity;
    }



    public int getCo2() {
        return co2;
    }

    public int getPm() {
        return pm;
    }

    public int getStatus() {
        return status;
    }



    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }



    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getLightIntensity() {
        return lightIntensity;
    }
}
