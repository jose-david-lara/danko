package com.wposs.danko.test.dto;

import com.google.gson.annotations.SerializedName;

public class TestDTO {

    @SerializedName("test")
    private String test;


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "test='" + test + '\'' +
                '}';
    }
}
