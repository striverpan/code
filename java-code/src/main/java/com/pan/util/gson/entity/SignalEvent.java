package com.pan.util.gson.entity;

/**
 * Created by pan on 2017/12/9.
 */
public class SignalEvent {

    private TrainData trainData;
    private Integer c_p1_11_0;

    public SignalEvent() {
    }

    public SignalEvent(Integer c_p1_11_0, TrainData trainData) {
        this.trainData = trainData;
        this.c_p1_11_0 = c_p1_11_0;
    }

    public TrainData getTrainData() {
        return this.trainData;
    }

    public void setTrainData(TrainData trainData) {
        this.trainData = trainData;
    }

    public void setC_p1_11_0(Integer c_p1_11_0) {
        this.c_p1_11_0 = c_p1_11_0;
    }

    public Integer getC_p1_11_0() {
        return this.c_p1_11_0;
    }

    public String toString() {
        return "SignalEvent(c_p1_11_0=" + this.c_p1_11_0 + "," + "trainData=" + this.trainData.toString() + "}";
    }
}
