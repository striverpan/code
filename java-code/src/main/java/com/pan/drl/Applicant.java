package com.pan.drl;

/**
 * Created by pan on 2017/11/29.
 */
public class Applicant {

    private String name;
    private int age;
    private boolean isValid;


    public Applicant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
