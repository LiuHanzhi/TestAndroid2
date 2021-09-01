package com.example.basic.extend;

public class ISon2Impl implements ISon2 {

    public void iSon2Method() {
        System.out.println("son2.iSon2Method");
    }

    @Override
    public void iSonMethod() {
        System.out.println("son2.iSonMethod");
    }

    @Override
    public void method() {
        System.out.println("son2.method");
    }
}
