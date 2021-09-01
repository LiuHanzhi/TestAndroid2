package com.example.basic.extend;

public class Father<T extends IFather> {

    protected T t;

    public Father(T t) {
        this.t = t;
    }

    public void method() {
        t.method();
    }



}
