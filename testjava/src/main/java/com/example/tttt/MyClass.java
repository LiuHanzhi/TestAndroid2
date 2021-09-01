package com.example.tttt;

public class MyClass {

    public static void main(String[] args){
        Parent parent = new Child();
        parent.aaa();

        Child child = new Child();
        ((Parent)child).aaa();
    }

    public static class Parent {
        protected void aaa() {
            System.out.println("Parent.aaa()");
        }
    }

    public static class Child extends Parent{
        @Override
        protected void aaa() {
            System.out.println("Child.aaa()");
        }
    }
}
