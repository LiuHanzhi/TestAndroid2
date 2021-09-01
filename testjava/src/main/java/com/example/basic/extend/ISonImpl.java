package com.example.basic.extend;

public class ISonImpl implements ISon {
    @Override
    public void iSonMethod() {
        System.out.println("son.iSonMethod");
    }

    @Override
    public void method() {
        System.out.println("son.method");
    }
}
