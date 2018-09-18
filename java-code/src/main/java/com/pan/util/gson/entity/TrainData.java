package com.pan.util.gson.entity;

import java.io.Serializable;

/**
 * Created by pan on 2017/12/9.
 */
public class TrainData implements Serializable{

    private static final long serialVersionUID = 2187171405467993594L;
    private Integer id;
    private String train_no;
    private String train_name;
    private String train_type_no;
    private String train_type_name;
    private String train_body_type;
    private Integer line_id;
    private String line_name;

    public TrainData() {
        super();
    }

    public TrainData(Integer id, String train_no, String train_name, String train_type_no, String train_type_name, String train_body_type, Integer line_id, String line_name) {
        super();
        this.id = id;
        this.train_no = train_no;
        this.train_name = train_name;
        this.train_type_no = train_type_no;
        this.train_type_name = train_type_name;
        this.train_body_type = train_body_type;
        this.line_id = line_id;
        this.line_name = line_name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_type_no() {
        return train_type_no;
    }

    public void setTrain_type_no(String train_type_no) {
        this.train_type_no = train_type_no;
    }

    public String getTrain_type_name() {
        return train_type_name;
    }

    public void setTrain_type_name(String train_type_name) {
        this.train_type_name = train_type_name;
    }

    public String getTrain_body_type() {
        return train_body_type;
    }

    public void setTrain_body_type(String train_body_type) {
        this.train_body_type = train_body_type;
    }

    public Integer getLine_id() {
        return line_id;
    }

    public void setLine_id(Integer line_id) {
        this.line_id = line_id;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    @Override
    public String toString() {
        return "TrainData{" +
                "id=" + id +
                ", train_no=" + train_no +
                ", train_name=" + train_name +
                ", train_type_no=" + train_type_no +
                ", train_type_name=" + train_type_name +
                ", train_body_type=" + train_body_type +
                ", line_id=" + line_id +
                ", line_name=" + line_name +
                '}';
    }
}
