package com.lhz.testmessenger.bean;

import java.io.Serializable;

/**
 * Created by liuhanzhi on 2018/3/24.
 */

public class TestBean implements Serializable {

    private int key;

    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
