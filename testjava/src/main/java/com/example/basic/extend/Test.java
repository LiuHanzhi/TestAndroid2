package com.example.basic.extend;

public class Test {
    public static void main(String[] args) {
        Father<IFather> father = new Father<>(new IFatherImpl());
        father.method();

        Son<ISon> son = new Son<>(new ISonImpl());
        son.method();
        son.iSonMethod();

        Son2 son2 = new Son2(new ISon2Impl());
        son2.method();
        son2.iSonMethod();
        son2.iSon2Method();
    }
}
